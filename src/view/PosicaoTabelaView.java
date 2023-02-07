package view;

import dominio.PosicaoTabela;
import impl.EstatisticasPosicaoTabela;

public class PosicaoTabelaView implements InterfaceView<EstatisticasPosicaoTabela> {

    @Override
    public void imprimir(EstatisticasPosicaoTabela estatistica) {
        System.out.println();
        System.out.println("## TABELA CAMPEONADO BRASILEIRO: " + estatistica.getAno() + " ##");
        int colocacao = 1;
        for (PosicaoTabela posicao : estatistica.getPosicoes()) {
            System.out.println(colocacao +". " + posicao);
            colocacao++;
        }
        System.out.println();
        System.out.println();
    }
}
