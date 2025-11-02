package ar.edu.unahur.obj2.observer.entidades;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.alertas.Alerta;
import ar.edu.unahur.obj2.observer.observadores.Observable;
import ar.edu.unahur.obj2.observer.riesgos.CriterioAplicable;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoCritico;

public class Entidad implements Observable {
    private final String nombre;
    private List<Alerta> alertasRecibidas = new ArrayList<Alerta>();
    private CriterioAplicable criterio = new RiesgoCritico();
    

    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(Alerta alerta) {
        if(! alertasRecibidas.contains(alerta)){
            alertasRecibidas.add(alerta);
        }
    }

    public Double riesgo(){
        return criterio.obtenerRiesgo(alertasRecibidas);
    }

    public void setCriterioRiesgo(CriterioAplicable criterio){
        this.criterio = criterio;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Alerta> getAlertasRecibidas() {
        return alertasRecibidas;
    }

    public CriterioAplicable getCriterio() {
        return criterio;
    }

    

}
