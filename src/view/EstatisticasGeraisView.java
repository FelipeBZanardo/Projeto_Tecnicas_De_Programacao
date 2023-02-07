package view;

import impl.EstatisticasGerais;

public class EstatisticasGeraisView implements InterfaceView<EstatisticasGerais> {

    @Override
    public void imprimir(EstatisticasGerais estatistica) {
        System.out.println("Estatisticas (Total de gols) - " + estatistica.getTotalDeGols());
        System.out.println("Estatisticas (Total de jogos) - " + estatistica.getTotalDeJogos());
        System.out.println("Estatisticas (Media de gols) - " + estatistica.getMediaDeGols());
        System.out.println("Estatisticas (Placar mais repetido) - "
                + estatistica.getPlacarMaisRepetido().getKey() + " ("
                + estatistica.getPlacarMaisRepetido().getValue() + " jogo(s))");

        System.out.println("Estatisticas (Placar menos repetido) - "
                + estatistica.getPlacarMenosRepetido().getKey() + " ("
                + estatistica.getPlacarMenosRepetido().getValue() + " jogo(s))");

        System.out.println("Estatisticas (3 ou mais gols) - " + estatistica.getJogosCom3OuMaisGols());
        System.out.println("Estatisticas (-3 gols) - " + estatistica.getJogosComMenos3Gols());

        System.out.println("Estatisticas (Vitorias Fora de casa) - " + estatistica.getTotalVitoriasVisitante());
        System.out.println("Estatisticas (Vitorias Em casa) - " + estatistica.getTotalVitoriasMandante());
        System.out.println("Estatisticas (Empates) - " + estatistica.getTotalEmpates());
    }
}
