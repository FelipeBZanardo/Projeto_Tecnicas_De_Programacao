package impl;

import dominio.Jogo;
import dominio.Time;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Campeonato implements InterfaceCampeonato{

    private final List<Time> times;
    private final Predicate<Jogo> FILTRO_ANO;
    private final List<Jogo> JOGOS_DO_ANO;

    public Campeonato(Predicate<Jogo> filtroAno, Stream<String> dadosComOsJogos) {
        FILTRO_ANO = filtroAno;
        JOGOS_DO_ANO = obterJogosDoAno(dadosComOsJogos);
        times = obterTimes();
    }

    @Override
    public List<Time> obterTimes() {
        return this.JOGOS_DO_ANO
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


    public List<Jogo> getJOGOS_DO_ANO() {
        return JOGOS_DO_ANO;
    }
}
