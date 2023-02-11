package impl;

import dominio.Jogo;
import dominio.Time;

import java.util.List;
import java.util.stream.Stream;

public interface InterfaceCampeonato {
    List<Time> obterTimes();
    List<Jogo> obterJogosPeloAno(Stream<String> dadosComOsJogos);

    List<Jogo> getJogosDoAno();

}
