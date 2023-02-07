package impl;

import dominio.Jogo;
import dominio.PosicaoTabela;
import dominio.Time;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstatisticasPosicaoTabela implements Estatistica, InterfaceEstatisticasPosicaoTabela{
    private final List<Time> times;
    private final List<Jogo> jogos;
    private final int ano;

    public EstatisticasPosicaoTabela(Campeonato campeonato) {
        this.times = campeonato.getTimes();
        this.jogos = campeonato.getJOGOS_DO_ANO();
        this.ano = jogos.get(0).data().data().getYear();

    }

    @Override
    public Set<PosicaoTabela> getPosicoes() {
        return times.stream()
                .map(time -> new PosicaoTabela(time,
                        vitoriasTime(time),
                        derrotasTime(time),
                        empatesTime(time),
                        golsFeitosPeloTime(time),
                        golsSofridosPeloTime(time),
                        saldoGolsDoTime(time),
                        (long) getTodosOsJogosPorTime().get(time).size(),
                        (vitoriasTime(time) * 3 + empatesTime(time))))
                .sorted(PosicaoTabela.posicaoTabelaComparator())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Long vitoriasTime(Time time){
        return getTodosOsJogosPorTime()
                .get(time)
                .stream()
                .filter(jogo -> jogo.vencedor().equals(time))
                .count();
    }

    private Long empatesTime(Time time){
        return getTodosOsJogosPorTime()
                .get(time)
                .stream()
                .filter(jogo -> jogo.vencedor().equals(new Time("-")))
                .count();
    }

    private Long derrotasTime(Time time){
        return getTodosOsJogosPorTime()
                .get(time)
                .stream()
                .filter(jogo -> !jogo.vencedor().equals(time))
                .filter(jogo -> !jogo.vencedor().equals(new Time("-")))
                .count();
    }

    private Long golsFeitosPeloTime(Time time){
        return getTodosOsJogosPorTime()
                .get(time)
                .stream()
                .mapToLong(jogo -> {
                    long somaGols = 0L;
                    if(jogo.mandante().equals(time))
                        somaGols += jogo.mandantePlacar();
                    if(jogo.visitante().equals(time))
                        somaGols += jogo.visitantePlacar();
                    return somaGols;})
                .sum();
    }

    private Long golsSofridosPeloTime(Time time){
        return getTodosOsJogosPorTime()
                .get(time)
                .stream()
                .mapToLong(jogo -> {
                    long somaGolsSofridos = 0L;
                    if(jogo.mandante().equals(time))
                        somaGolsSofridos += jogo.visitantePlacar();
                    if(jogo.visitante().equals(time))
                        somaGolsSofridos += jogo.mandantePlacar();
                    return somaGolsSofridos;})
                .sum();
    }

    private Long saldoGolsDoTime(Time time){
        return golsFeitosPeloTime(time) - golsSofridosPeloTime(time);
    }

    private Map<Time, List<Jogo>> getTodosOsJogosPorTimeComoMandantes() {
        return jogos
                .stream()
                .collect(Collectors.groupingBy(
                        Jogo::mandante));
    }

    private Map<Time, List<Jogo>> getTodosOsJogosPorTimeComoVisitante() {
        return jogos
                .stream()
                .collect(Collectors.groupingBy(
                        Jogo::visitante));
    }

    public Map<Time, List<Jogo>> getTodosOsJogosPorTime() {
        return Stream.of(getTodosOsJogosPorTimeComoVisitante(), getTodosOsJogosPorTimeComoMandantes())
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (lista1, lista2) -> {
                            lista1.addAll(lista2);
                            return lista1;}));
    }

    public int getAno() {
        return ano;
    }
}
