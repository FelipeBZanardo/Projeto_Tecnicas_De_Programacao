package view;
import impl.Estatistica;
import impl.EstatisticasGerais;

import static dominio.enums.MaisOuMenos.MAIS;
import static dominio.enums.MaisOuMenos.MENOS;
import static dominio.enums.TipoPlacar.MAIS_REPETIDO;
import static dominio.enums.TipoPlacar.MENOS_REPETIDO;
import static dominio.enums.TipoResultado.*;

public class EstatisticasGeraisView implements InterfaceView<EstatisticasGerais> {

    @Override
    public void imprimir(EstatisticasGerais estatistica) {
        System.out.println("Estatisticas (Total de gols) - " + estatistica.getEstatisticasPorJogo().getSum());
        System.out.println("Estatisticas (Total de jogos) - " + estatistica.getEstatisticasPorJogo().getCount());
        System.out.println("Estatisticas (Media de gols) - " + estatistica.getEstatisticasPorJogo().getAverage());
        System.out.println("Estatisticas (Placar mais repetido) - "
                + estatistica.getPlacarRepetido(MAIS_REPETIDO).getKey() + " ("
                + estatistica.getPlacarRepetido(MAIS_REPETIDO).getValue() + " jogo(s))");

        System.out.println("Estatisticas (Placar menos repetido) - "
                + estatistica.getPlacarRepetido(MENOS_REPETIDO).getKey() + " ("
                + estatistica.getPlacarRepetido(MENOS_REPETIDO).getValue() + " jogo(s))");

        System.out.println("Estatisticas (3 ou mais gols) - " + estatistica.getJogosComMaisOuMenosDe(MAIS, 2));
        System.out.println("Estatisticas (-3 gols) - " + estatistica.getJogosComMaisOuMenosDe(MENOS, 3));

        System.out.println("Estatisticas (Vitorias Fora de casa) - " + estatistica.getTotalDe(VITORIA_VISITANTE));
        System.out.println("Estatisticas (Vitorias Em casa) - " + estatistica.getTotalDe(VITORIA_MANDANTE));
        System.out.println("Estatisticas (Empates) - " + estatistica.getTotalDe(EMPATE));
    }
}
