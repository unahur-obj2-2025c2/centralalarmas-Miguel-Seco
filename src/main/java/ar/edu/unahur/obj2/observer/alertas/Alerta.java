package ar.edu.unahur.obj2.observer.alertas;


public class Alerta {

    private final String tipo;
    private final Integer nivel;

    public Alerta(String tipo, Integer nivel) {
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Integer getNivel() {
        return this.nivel;
    }

    public Boolean esCritica() {
        return Boolean.valueOf(this.nivel >= 8);
    }

}
