package dominio;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class DiaDaSemana {
    private static final Map<String, DayOfWeek> MAP_DIA_DA_SEMANA = new HashMap<>();
    public static final DiaDaSemana DIA_DA_SEMANA = DiaDaSemana.getInstance();

    private DiaDaSemana() {
        MAP_DIA_DA_SEMANA.put("Domingo", DayOfWeek.SUNDAY);
        MAP_DIA_DA_SEMANA.put("Segunda-feira", DayOfWeek.MONDAY);
        MAP_DIA_DA_SEMANA.put("Terça-feira", DayOfWeek.TUESDAY);
        MAP_DIA_DA_SEMANA.put("Quarta-feira", DayOfWeek.WEDNESDAY);
        MAP_DIA_DA_SEMANA.put("Quinta-feira", DayOfWeek.THURSDAY);
        MAP_DIA_DA_SEMANA.put("Sexta-feira", DayOfWeek.FRIDAY);
        MAP_DIA_DA_SEMANA.put("Sábado", DayOfWeek.SATURDAY);
    }

    private static DiaDaSemana getInstance(){
        return new DiaDaSemana();
    }

    public DayOfWeek obterDiaDaSemana(String dia){
        return DiaDaSemana.MAP_DIA_DA_SEMANA.get(dia);
    }


}
