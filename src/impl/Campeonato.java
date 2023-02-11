package impl;

import dominio.Jogo;
import dominio.Time;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Campeonato implements InterfaceCampeonato{

    private final Predicate<Jogo> FILTRO_ANO;
    private final List<Jogo> jogosDoAno;

    public Campeonato(Predicate<Jogo> filtroAno, Stream<String> dadosComOsJogos) {
        FILTRO_ANO = filtroAno;
        this.jogosDoAno = obterJogosDoAno(dadosComOsJogos);
    }

    @Override
    public List<Time> obterTimes() {
        return this.jogosDoAno
                .stream()
                .map(Jogo::mandante)
                .collect(Collectors.toSet())
                .stream()
                .toList();
    }

    @Override
    public List<Jogo> obterJogosDoAno(Stream<String> dadosComOsJogos) {
        return dadosComOsJogos
                .skip(1)
                .map(linha -> linha.split(";"))
                .map(Jogo::obterJogo)
                .filter(this.FILTRO_ANO)
                .toList();
    }

    public List<Jogo> getJogosDoAno() {
        return jogosDoAno;
    }
}
