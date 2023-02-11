package impl;

import dominio.enums.MaisOuMenos;
import dominio.Resultado;
import dominio.enums.TipoPlacar;
import dominio.enums.TipoResultado;

import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

public class EstatisticasGerais extends Estatistica implements InterfaceEstatisticasGerais {

    public EstatisticasGerais(Campeonato campeonato) {
        super(campeonato);
    }

    @Override
    public IntSummaryStatistics getEstatisticasPorJogo() {
        return campeonato
                .getJogosDoAno()
                .stream()
                .mapToInt(jogo -> jogo.mandantePlacar() + jogo.visitantePlacar())
                .summaryStatistics();
    }

    private Map<Resultado, Long> contagemDeCadaResultado() {
        return campeonato
                .getJogosDoAno()
                .stream()
                .collect(Collectors.groupingBy(
                        jogo -> new Resultado(jogo.mandantePlacar(), jogo.visitantePlacar()),
                        Collectors.counting()));
    }

    @Override
    public Map.Entry<Resultado, Long> getPlacarRepetido(TipoPlacar tipoPlacar) {
        return contagemDeCadaResultado()
                .entrySet()
                .stream()
                .max(tipoPlacar.getComparator())
                .get();
    }

    @Override
    public Long getJogosComMaisOuMenosDe(MaisOuMenos maisOuMenos, int gols) {
        return this.campeonato
                .getJogosDoAno()
                .stream()
                .mapToLong(jogo -> jogo.mandantePlacar() + jogo.visitantePlacar())
                .filter(quantidade -> {
                    if (maisOuMenos.equals(MaisOuMenos.MAIS))
                        return quantidade > gols;
                    return quantidade < gols;
                })
                .count();
    }

    @Override
    public Long getTotalDe(TipoResultado tipoResultado) {
        return this.campeonato
                .getJogosDoAno()
                .stream()
                .filter(tipoResultado.getFiltro())
                .count();
    }

}
