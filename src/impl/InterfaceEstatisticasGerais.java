package impl;

import dominio.enums.MaisOuMenos;
import dominio.Resultado;
import dominio.enums.TipoPlacar;
import dominio.enums.TipoResultado;
import java.util.IntSummaryStatistics;
import java.util.Map;

public interface InterfaceEstatisticasGerais {
    IntSummaryStatistics getEstatisticasPorJogo();
    Map.Entry<Resultado, Long> getPlacarRepetido(TipoPlacar tipoPlacar);
    Long getJogosComMaisOuMenosDe(MaisOuMenos maisOuMenos, int gols);
    Long getTotalDe(TipoResultado tipoResultado);
}
