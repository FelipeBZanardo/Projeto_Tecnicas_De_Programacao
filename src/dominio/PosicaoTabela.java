package dominio;

import impl.Estatistica;

import java.util.Comparator;

public record PosicaoTabela(Time time,
                            Long vitorias,
                            Long derrotas,
                            Long empates,
                            Long golsPositivos,
                            Long golsSofridos,
                            Long saldoDeGols,
                            Long jogos,
                            Long pontos) {

    @Override
    public String toString() {
        return  time +
                ", pontos=" + pontos + // desenvolver forma de obter a pontuação
                ", vitorias=" + vitorias +
                ", derrotas=" + derrotas +
                ", empates=" + empates +
                ", golsPositivos=" + golsPositivos +
                ", golsSofridos=" + golsSofridos +
                ", saldoDeGols=" + saldoDeGols +
                ", jogos=" + jogos +
                '}';
    }

    public static Comparator<PosicaoTabela> posicaoTabelaComparator(){
        return Comparator.comparing(PosicaoTabela::pontos, Comparator.reverseOrder())
                .thenComparing(PosicaoTabela::vitorias, Comparator.reverseOrder())
                .thenComparing(PosicaoTabela::saldoDeGols, Comparator.reverseOrder())
                .thenComparing(PosicaoTabela::golsPositivos, Comparator.reverseOrder());
    }
}
