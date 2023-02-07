package view;

import impl.Estatistica;

public interface InterfaceView<T extends Estatistica> {
     void imprimir(T estatistica);
}
