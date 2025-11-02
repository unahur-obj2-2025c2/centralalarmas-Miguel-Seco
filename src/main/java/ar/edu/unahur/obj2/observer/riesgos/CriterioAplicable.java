package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.observer.alertas.Alerta;

public interface CriterioAplicable {

    Double obtenerRiesgo(List<Alerta> alertasRecibidas);

}
