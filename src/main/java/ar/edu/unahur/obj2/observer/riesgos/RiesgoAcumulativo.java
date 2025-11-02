package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.observer.alertas.Alerta;

public class RiesgoAcumulativo implements CriterioAplicable {

    @Override
    public Double obtenerRiesgo(List<Alerta> alertasRecibidas) {
        return alertasRecibidas.stream().filter(a -> a.esCritica()).mapToDouble(a -> a.getNivel()).sum();
    }

}
