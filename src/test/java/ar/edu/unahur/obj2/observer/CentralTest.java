package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.alertas.Alerta;
import ar.edu.unahur.obj2.observer.centrales.Central;
import ar.edu.unahur.obj2.observer.entidades.Entidad;
import ar.edu.unahur.obj2.observer.excepciones.NivelAlertaException;
import ar.edu.unahur.obj2.observer.riesgos.CriterioAplicable;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoAcumulativo;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoPromedio;

public class CentralTest {
    private Alerta lluvia; 
    private Alerta calor; 
    private Central central;
    private Entidad e1;
    private Entidad e2;

    @BeforeEach
    public void setUp(){
        lluvia =  new Alerta("lluvia", 6);
        calor = new Alerta("calor", 8);
        central = new Central();
        e1 = new Entidad("Hospital");
        e2 = new Entidad("Bomberos");
        central.agregarObservador(e1);
        central.agregarObservador(e2);
    }

    @Test
    @DisplayName("dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo")
    public void test1(){
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivel());
        assertEquals("lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());

        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());
        assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivel());
    }

    @Test
    @DisplayName("dadoElSetUp_alCambiarElComportamientoYAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo")
    public void test2(){
        CriterioAplicable promedio = new RiesgoPromedio();
        e1.setCriterioRiesgo(promedio);
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);
       
        assertEquals(7, e1.riesgo());
        assertEquals("lluvia",e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals("calor",e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(2,e1.getAlertasRecibidas().size());

        assertEquals(10, e2.riesgo());
        assertEquals("lluvia",e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals("calor",e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(2,e2.getAlertasRecibidas().size());

        assertEquals(4,central.cantidadDeAlertasNotificadas());
    }

    @Test 
    public void dadaUnaCentralConDosEntidadesquitarObservador(){
        assertEquals(2, central.getObservadores().size());
        central.quitarObservador(e1);
        assertEquals(1, central.getObservadores().size());
    }

    @Test
    @DisplayName("dadoElSetUp_alAgregarAlertasQuitarUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo")
    public void test3(){
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);
        central.quitarObservador(e1);
        central.emitirAlerta("granizo", 7);
        assertEquals(2,e1.getAlertasRecibidas().size());
        assertEquals(10, e1.riesgo());
        assertEquals(3,e2.getAlertasRecibidas().size());
        assertEquals(7, e2.riesgo());
        assertEquals(5,central.cantidadDeAlertasNotificadas());

    }

    @Test 
    public void test4(){
        assertThrows(NivelAlertaException.class, () -> {central.emitirAlerta("fuego", 11);});
    }

    @Test 
    public void dadaUnaCentralCambiarACriterioAcumulativo(){
        CriterioAplicable acumulativo = new RiesgoAcumulativo();
        e1.setCriterioRiesgo(acumulativo);
        central.emitirAlerta("calor", 9);
        central.emitirAlerta("lluvia", 8);
        assertEquals("Hospital", e1.getNombre());
        assertTrue(acumulativo.equals(e1.getCriterio()));

        assertEquals(17, e1.riesgo());

        
    }
}
