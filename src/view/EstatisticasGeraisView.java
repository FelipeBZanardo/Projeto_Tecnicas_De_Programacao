package view;

import dominio.enums.MaisOuMenos;
import dominio.enums.TipoPlacar;
import dominio.enums.TipoResultado;
import impl.EstatisticasGerais;

public class EstatisticasGeraisView implements InterfaceView<EstatisticasGerais> {

    @Override
    public void imprimir(EstatisticasGerais estatistica) {
        System.out.println("Estatisticas (Total de gols) - " + estatistica.getEstatisticasPorJogo().getSum());
        System.out.println("Estatisticas (Total de jogos) - " + estatistica.getEstatisticasPorJogo().getCount());
        System.out.println("Estatisticas (Media de gols) - " + estatistica.getEstatisticasPorJogo().getAverage());
        System.out.println("Estatisticas (Placar mais repetido) - "
                + estatistica.getPlacarRepetido(TipoPlacar.MAIS_REPETIDO).getKey() + " ("
                + estatistica.getPlacarRepetido(TipoPlacar.MAIS_REPETIDO).getValue() + " jogo(s))");

        System.out.println("Estatisticas (Placar menos repetido) - "
                + estatistica.getPlacarRepetido(TipoPlacar.MENOS_REPETIDO).getKey() + " ("
                + estatistica.getPlacarRepetido(TipoPlacar.MENOS_REPETIDO).getValue() + " jogo(s))");

        System.out.println("Estatisticas (3 ou mais gols) - " + estatistica.getJogosComMaisOuMenosDe(MaisOuMenos.MAIS, 2));
        System.out.println("Estatisticas (-3 gols) - " + estatistica.getJogosComMaisOuMenosDe(MaisOuMenos.MENOS, 3));

        System.out.println("Estatisticas (Vitorias Fora de casa) - " + estatistica.getTotalDe(TipoResultado.VITORIA_VISITANTE));
        System.out.println("Estatisticas (Vitorias Em casa) - " + estatistica.getTotalDe(TipoResultado.VITORIA_MANDANTE));
        System.out.println("Estatisticas (Empates) - " + estatistica.getTotalDe(TipoResultado.EMPATE));
    }
}
