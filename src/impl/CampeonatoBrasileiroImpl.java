package impl;

import dominio.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CampeonatoBrasileiroImpl {

    private Map<Integer, List<Jogo>> brasileirao;
    private List<Jogo> jogos;
    private Predicate<Jogo> filtro;

    public CampeonatoBrasileiroImpl(Path arquivo, Predicate<Jogo> filtro) throws IOException {
        this.jogos = lerArquivo(arquivo);
        this.filtro = filtro;
        this.brasileirao = jogos.stream()
                .filter(filtro) //filtrar por ano
                .collect(Collectors.groupingBy(
                        Jogo::rodada,
                        Collectors.mapping(Function.identity(), Collectors.toList())));

    }

    public List<Jogo> lerArquivo(Path file) throws IOException {
        List<String> linhasLidas = Files.readAllLines(file);
        return linhasLidas.stream()
                .skip(1)
                .map(linha -> linha.split(";"))
                .map(this::obterJogo)
                .toList();
    }

    private Jogo obterJogo(String[] atributosJogo){
        return new Jogo(Integer.parseInt(atributosJogo[0]),
                obterDataDoJogo(atributosJogo[1],atributosJogo[2],atributosJogo[3]),
                new Time(atributosJogo[4]),
                new Time(atributosJogo[5]),
                new Time(atributosJogo[6]),
                atributosJogo[7],
                Integer.parseInt(atributosJogo[8]),
                Integer.parseInt(atributosJogo[9]),
                atributosJogo[10],
                atributosJogo[11],
                atributosJogo[12]);
    }

    private DataDoJogo obterDataDoJogo(String data, String hora, String dia){
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate dataLocalDate = LocalDate.parse(data, formatterData);
        LocalTime horaLocalTime;
        try{
            horaLocalTime = LocalTime.parse(hora.replace('h', ':'), formatterHora);
        } catch (DateTimeParseException e){
            horaLocalTime = null;
        }
        DayOfWeek diaDaSemana = DiaDaSemana.DIA_DA_SEMANA.obterDiaDaSemana(dia);
        return new DataDoJogo(dataLocalDate, horaLocalTime, diaDaSemana);
    }

    public IntSummaryStatistics getEstatisticasPorJogo() {
        return todosOsJogos()
                .stream()
                .mapToInt(jogo -> jogo.mandantePlacar() + jogo.visitantePlacar())
                .summaryStatistics();
    }

    public Map<Jogo, Integer> getMediaGolsPorJogo() {
        return null;
    }

    public IntSummaryStatistics GetEstatisticasPorJogo() {
        return null;
    }

    public List<Jogo> todosOsJogos() {
        return this.brasileirao
                .values()
                .stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public Long getTotalVitoriasEmCasa() {
        return todosOsJogos()
                .stream()
                .filter(jogo -> jogo.mandantePlacar() > jogo.visitantePlacar())
                .count();
    }

    public Long getTotalVitoriasForaDeCasa() {
        return todosOsJogos()
                .stream()
                .filter(jogo -> jogo.visitantePlacar() > jogo.mandantePlacar())
                .count();
    }

    public Long getTotalEmpates() {
        return todosOsJogos()
                .stream()
                .filter(jogo -> jogo.mandantePlacar().equals(jogo.visitantePlacar()))
                .count();
    }

    public Long getTotalJogosComMenosDe3Gols() {
        return todosOsJogos()
                .stream()
                .filter(jogo -> (jogo.mandantePlacar() + jogo.visitantePlacar()) < 3)
                .count();
    }

    public Long getTotalJogosCom3OuMaisGols() {
        return todosOsJogos()
                .stream()
                .filter(jogo -> (jogo.mandantePlacar() + jogo.visitantePlacar()) >= 3)
                .count();
    }

    public Map<Resultado, Long> getTodosOsPlacares() {
        return todosOsJogos()
                .stream()
                .collect(Collectors.groupingBy(
                        jogo -> new Resultado(jogo.mandantePlacar(), jogo.visitantePlacar()),
                        Collectors.counting()));
    }

    public Map.Entry<Resultado, Long> getPlacarMaisRepetido() {
        return getTodosOsPlacares()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();
    }

    public Map.Entry<Resultado, Long> getPlacarMenosRepetido() {
        return getTodosOsPlacares()
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get();
    }

    private List<Time> getTodosOsTimes() {
        return null;
    }

    private Map<Time, List<Jogo>> getTodosOsJogosPorTimeComoMandantes() {
        return null;
    }

    private Map<Time, List<Jogo>> getTodosOsJogosPorTimeComoVisitante() {
        return null;
    }

    public Map<Time, List<Jogo>> getTodosOsJogosPorTime() {
        return null;
    }

    public Map<Time, Map<Boolean, List<Jogo>>> getJogosParticionadosPorMandanteTrueVisitanteFalse() {
        return null;
    }

    public Set<PosicaoTabela> getTabela() {
        return null;
    }

    private DayOfWeek getDayOfWeek(String dia) {
        return null;
    }

    private Map<Integer, Integer> getTotalGolsPorRodada() {
        return null;
    }

    private Map<Time, Integer> getTotalDeGolsPorTime() {
        return null;
    }

    private Map<Integer, Double> getMediaDeGolsPorRodada() {
        return null;
    }


}