package org.ualhmis.torneos;

import java.util.ArrayList;
import java.util.List;

class GestorTorneos {
    private List<Torneo> torneos;

    public List<Torneo> getTorneos() {
        return torneos;
    }

    public GestorTorneos() {
        this.torneos = new ArrayList<>();
    }

    public void crearTorneo(String nombre, String deporte, String categoria, String modalidad, String tipo, Sede sede) {
        if (sede == null) {
            throw new IllegalArgumentException("La sede no puede ser nula.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del torneo no puede ser nulo o vacío.");
        }
        torneos.add(new Torneo(nombre, deporte, categoria, modalidad, tipo, sede));
    }
}