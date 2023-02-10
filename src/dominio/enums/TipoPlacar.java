package dominio.enums;

import dominio.Resultado;

import java.util.Comparator;
import java.util.Map;

public enum TipoPlacar {
    MAIS_REPETIDO(Map.Entry.comparingByValue()),
    MENOS_REPETIDO(Map.Entry.comparingByValue(java.util.Comparator.reverseOrder()));

    private final Comparator<Map.Entry<Resultado, Long>> Comparator;

    TipoPlacar(java.util.Comparator<Map.Entry<Resultado, Long>> comparator) {
        Comparator = comparator;
    }

    public java.util.Comparator<Map.Entry<Resultado, Long>> getComparator() {
        return Comparator;
    }
}
