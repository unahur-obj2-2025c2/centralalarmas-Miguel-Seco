package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void dadaUnaAlertaObtengoElTipo(){
        String valorEsperado = "lluvia";
        String valorObtenido = lluvia.getTipo();
        assertEquals(valorEsperado, valorObtenido );
    }

    @Test
    public void dadaUnaAlertaObtengoElNivel(){
        Integer valorEsperado = 6;
        Integer valorObtenido = lluvia.getNivel();
        assertEquals(valorEsperado, valorObtenido );
    }

    @Test
    public void dadaUnaAlertaDeNivel9ObtengoQueEsCritica(){
        Alerta huracan = new Alerta("Huracan", 9); 
        assertTrue(huracan.esCritica());        
    }

    @Test
    public void dadaUnaAlertaDeNivel6ObtengoQueNoEsCritica(){ 
        assertFalse(lluvia.esCritica());        
    }

    @Test
    public void dadaUnaAlertaDeNivel10ObtengoQueEsCritica(){ 
        assertTrue(fuego.esCritica());        
    }
}
