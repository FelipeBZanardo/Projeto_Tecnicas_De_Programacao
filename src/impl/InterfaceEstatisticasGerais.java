package impl;

import dominio.Jogo;
import dominio.Resultado;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

public interface InterfaceEstatisticasGerais {
    IntSummaryStatistics getEstatisticasPorJogo(List<Jogo> jogos);
    Map<Resultado, Long> contagemDeCadaResultado(List<Jogo> jogos);
}
