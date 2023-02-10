package dominio.enums;

import dominio.Jogo;

import java.util.function.Predicate;

public enum TipoResultado {
    VITORIA_MANDANTE(jogo -> jogo.mandantePlacar() > jogo.visitantePlacar()),
    VITORIA_VISITANTE(jogo -> jogo.mandantePlacar() < jogo.visitantePlacar()),
    EMPATE(jogo -> jogo.mandantePlacar().equals(jogo.visitantePlacar()));

    private final Predicate<Jogo> filtro;

    TipoResultado(Predicate<Jogo> filtro) {
        this.filtro = filtro;
    }

    public Predicate<Jogo> getFiltro() {
        return filtro;
    }
}
