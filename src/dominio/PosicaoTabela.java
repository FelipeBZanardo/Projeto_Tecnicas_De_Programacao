package dominio;

public record PosicaoTabela(Time time,
                            Long vitorias,
                            Long derrotas,
                            Long empates,
                            Long golsPositivos,
                            Long golsSofridos,
                            Long saldoDeGols,
                            Long jogos,
                            Long pontos) {


    public PosicaoTabela(Time time, Long vitorias, Long derrotas, Long empates, Long golsPositivos, Long golsSofridos, Long saldoDeGols, Long jogos) {
        this(time, vitorias, derrotas, empates, golsPositivos, golsSofridos, saldoDeGols, jogos, (vitorias * 3 + empates));
    }

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
}
