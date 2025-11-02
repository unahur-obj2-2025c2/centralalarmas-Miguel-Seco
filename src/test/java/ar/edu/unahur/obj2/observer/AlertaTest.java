package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

import ar.edu.unahur.obj2.observer.alertas.Alerta;

public class AlertaTest {
    private Alerta lluvia; 
    private Alerta calor;
    private Alerta fuego; 

    @BeforeEach
    public void setUp(){
        lluvia =  new Alerta("lluvia", 6);
        calor = new Alerta("calor", 8);
        fuego = new Alerta("fuego", 10);
    }

    public void dadaUnaAlertaObtengoElTipo(){
        String valorEsperado = "lluvia";
        String valorObtenido = lluvia.getTipo();
        assertEquals(valorEsperado, valorObtenido );
    }

    public void dadaUnaAlertaObtengoElNivel(){
        Integer valorEsperado = 6;
        Integer valorObtenido = lluvia.getNivel();
        assertEquals(valorEsperado, valorObtenido );
    }

    public void dadaUnaAlertaDeNivel9ObtengoQueEsCritica(){
        Alerta huracan = new Alerta("Huracan", 9); 
        assertTrue(huracan.esCritica());        
    }

    public void dadaUnaAlertaDeNivel8ObtengoQueNoEsCritica(){ 
        assertFalse(calor.esCritica());        
    }

    public void dadaUnaAlertaDeNivel10ObtengoQueEsCritica(){ 
        assertTrue(fuego.esCritica());        
    }
}
