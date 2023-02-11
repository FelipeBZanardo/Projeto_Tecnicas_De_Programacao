import Persistence.Arquivo;
import dominio.Jogo;
import impl.Campeonato;
import impl.Estatistica;
import impl.EstatisticasGerais;
import impl.EstatisticasPosicaoTabela;
import view.EstatisticasGeraisView;
import view.PosicaoTabelaView;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

public class Aplicacao {

    public static void main(String[] args) throws IOException {

        // obter caminho do arquivo
        Path file = Path.of("campeonato-brasileiro.csv");

        //filtro para o ano do campeonato
        Predicate<Jogo> filtro = (jogo) -> jogo.data().data().getYear() == 2020
                || jogo.data().data().getYear() == 2021;

        //Cria o Campeonato
        Campeonato campeonato = new Campeonato(filtro, Arquivo.lerArquivo(file));

        // imprimir estatísticas
        EstatisticasGerais estatisticasGerais = new EstatisticasGerais(campeonato);
        EstatisticasGeraisView estatisticasGeraisView = new EstatisticasGeraisView();
        estatisticasGeraisView.imprimir(estatisticasGerais);

        // imprimir tabela ordenada
        EstatisticasPosicaoTabela estatisticasPosicaoTabela = new EstatisticasPosicaoTabela(campeonato);
        PosicaoTabelaView posicaoTabelaView = new PosicaoTabelaView();
        posicaoTabelaView.imprimir(estatisticasPosicaoTabela);

    }
}