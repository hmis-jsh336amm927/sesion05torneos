package org.ualhmis.torneos;
import java.util.ArrayList;
import java.util.List;

public class Sede {
    private String nombre;
    private List<Instalacion> instalaciones;

    public Sede(String nombre) {
        this.nombre = nombre;
        this.instalaciones = new ArrayList<>();
    }

    public void agregarInstalacion(Instalacion instalacion) {
        instalaciones.add(instalacion);
    }

    public List<Instalacion> getInstalaciones() {
        return instalaciones;
    }
}
