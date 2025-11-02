package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.alertas.Alerta;

public interface ObservadoresPosibles {
    void agregarObservador(Observable observador);
    void quitarObservador(Observable observador);
    void Notificar(Alerta alerta);
}
