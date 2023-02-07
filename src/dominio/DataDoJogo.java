package dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public record DataDoJogo(LocalDate data,
                         LocalTime horario,
                         DayOfWeek dia){

    public static DataDoJogo obterDataDoJogo(String data, String hora, String dia){
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
}