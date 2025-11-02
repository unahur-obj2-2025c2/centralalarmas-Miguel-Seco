package ar.edu.unahur.obj2.observer.centrales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unahur.obj2.observer.alertas.Alerta;
import ar.edu.unahur.obj2.observer.excepciones.NivelAlertaException;
import ar.edu.unahur.obj2.observer.observadores.Observable;
import ar.edu.unahur.obj2.observer.observadores.ObservadoresPosibles;

public class Central implements ObservadoresPosibles{

    private List <Observable> observadores = new ArrayList<Observable>();
    private Map <Alerta,Integer> registroDeAlertas = new HashMap<Alerta,Integer>();

    public void emitirAlerta(String tipo, Integer nivel){
        if(nivel < 1 || nivel > 10 ){
            throw new NivelAlertaException("Nivel de alerta incorrecto ");
        }
        Alerta alertaEmitida = new Alerta(tipo, nivel);
        this.Notificar(alertaEmitida);
        registroDeAlertas.put(alertaEmitida,observadores.size());
    }

    @Override
    public void agregarObservador(Observable observador) {
        if(!observadores.contains(observador)){
            observadores.add(observador);
        }
    }

    @Override
    public void quitarObservador(Observable observador) {
        if(observadores.contains(observador)){
            observadores.remove(observador);
        }
    }

    @Override
    public void Notificar(Alerta alerta) {
        observadores.forEach(o -> o.actualizar(alerta));
    }

    public Integer cantidadDeAlertasNotificadas(){
        return registroDeAlertas.values().stream().mapToInt(Integer :: intValue).sum();
    }

    public List<Observable> getObservadores() {
        return observadores;
    }

    
}
