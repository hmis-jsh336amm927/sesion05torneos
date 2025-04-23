package org.ualhmis.torneos;
import java.util.ArrayList;
import java.util.List;

public class Sede {
    private String nombre;
    private List<Instalacion> instalaciones;

    public Sede(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sede no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
        this.instalaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInstalaciones(List<Instalacion> instalaciones) {
        this.instalaciones = instalaciones;
    }

    public void agregarInstalacion(Instalacion instalacion) {
        if (instalacion == null ) {
            throw new IllegalArgumentException("La instalacion de la sede no puede ser nulo o vacío.");
        }
        instalaciones.add(instalacion);
    }

    public List<Instalacion> getInstalaciones() {
        return instalaciones;
    }
}
