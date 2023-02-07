package impl;

import dominio.Jogo;
import dominio.Resultado;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EstatisticasGerais implements InterfaceEstatisticasGerais, Estatistica {

    private final List<Jogo> jogos;
    private final Long totalDeGols;
    private final Long totalDeJogos;
    private final Double mediaDeGols;
    private Map.Entry<Resultado, Long> placarMaisRepetido;
    private Map.Entry<Resultado, Long> placarMenosRepetido;
    private Long jogosCom3OuMaisGols;
    private Long jogosComMenos3Gols;
    private Long totalVitoriasMandante;
    private Long totalVitoriasVisitante;
    private Long totalEmpates;

    public EstatisticasGerais(List<Jogo> jogos) {
        this.jogos = jogos;
        this.totalDeGols = getEstatisticasPorJogo(jogos).getSum();
        this.totalDeJogos = getEstatisticasPorJogo(jogos).getCount();
        this.mediaDeGols = getEstatisticasPorJogo(jogos).getAverage();
        setPlacarMaisRepetido();
        setPlacarMenosRepetido();
        setJogosCom3OuMaisGols();
        setJogosComMenos3Gols();
        setTotalVitoriasMandante();
        setTotalVitoriasVisitante();
        setTotalEmpates();
    }

    @Override
    public IntSummaryStatistics getEstatisticasPorJogo(List<Jogo> jogos) {
        return jogos
                .stream()
                .mapToInt(jogo -> jogo.mandantePlacar() + jogo.visitantePlacar())
                .summaryStatistics();
    }

    @Override
    public Map<Resultado, Long> contagemDeCadaResultado(List<Jogo> jogos) {
        return jogos
                .stream()
                .collect(Collectors.groupingBy(
                        jogo -> new Resultado(jogo.mandantePlacar(), jogo.visitantePlacar()),
                        Collectors.counting()));
    }

    private void setPlacarMaisRepetido() {
        this.placarMaisRepetido = contagemDeCadaResultado(this.jogos)
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();
    }

    public Map.Entry<Resultado, Long> getPlacarMaisRepetido() {
        return placarMaisRepetido;
    }

    public Map.Entry<Resultado, Long> getPlacarMenosRepetido() {
        return placarMenosRepetido;
    }

    private void setPlacarMenosRepetido() {
        this.placarMenosRepetido = contagemDeCadaResultado(this.jogos)
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get();
    }

    public Long getJogosCom3OuMaisGols() {
        return jogosCom3OuMaisGols;
    }

    private void setJogosCom3OuMaisGols() {
        this.jogosCom3OuMaisGols = this.jogos
                .stream()
                .filter(jogo -> (jogo.mandantePlacar() + jogo.visitantePlacar()) >= 3)
                .count();
    }

    public Long getJogosComMenos3Gols() {
        return jogosComMenos3Gols;
    }

    private void setJogosComMenos3Gols() {
        this.jogosComMenos3Gols = this.jogos
                .stream()
                .filter(jogo -> (jogo.mandantePlacar() + jogo.visitantePlacar()) < 3)
                .count();
    }

    public Long getTotalVitoriasMandante() {
        return totalVitoriasMandante;
    }

    private void setTotalVitoriasMandante() {
        this.totalVitoriasMandante = this.jogos
                .stream()
                .filter(jogo -> jogo.mandantePlacar() > jogo.visitantePlacar())
                .count();
    }

    public Long getTotalVitoriasVisitante() {
        return totalVitoriasVisitante;
    }

    private void setTotalVitoriasVisitante() {
        this.totalVitoriasVisitante = this.jogos
                .stream()
                .filter(jogo -> jogo.mandantePlacar() < jogo.visitantePlacar())
                .count();
    }

    public Long getTotalEmpates() {
        return totalEmpates;
    }

    public void setTotalEmpates() {
        this.totalEmpates = this.jogos
                .stream()
                .filter(jogo -> Objects.equals(jogo.mandantePlacar(), jogo.visitantePlacar()))
                .count();
    }

    public Long getTotalDeGols() {
        return totalDeGols;
    }

    public Long getTotalDeJogos() {
        return totalDeJogos;
    }

    public Double getMediaDeGols() {
        return mediaDeGols;
    }
}
