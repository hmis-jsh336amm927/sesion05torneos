package org.ualhmis.torneos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class Torneo {
    private String nombre;
    private String deporte;
    private String categoria;
    private String modalidad;
    private List<Equipo> equipos;
    private String tipo;
	private Sede sede; 

	public Torneo(String nombre, String deporte, String categoria, String modalidad, String tipo, Sede sede) {
        setNombre(nombre);
        this.deporte = deporte;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.tipo = tipo;
        setSede(sede); 
        this.equipos = new ArrayList<>();
    }

	public void setSede(Sede sede) {
        if (sede == null) {
            throw new IllegalArgumentException("El torneo debe estar asociado a una sede específica");
        }
        this.sede = sede;
    }

	public Sede getSede() {
		return sede;
	}

    public void registrarEquipo(Equipo equipo) {
        if (equipo == null) {
            throw new IllegalArgumentException("El equipo no puede ser null");
        }
        if (!equipo.getCategoria().equals(this.categoria) || !equipo.getModalidad().equals(this.modalidad)) {
            throw new IllegalArgumentException("El equipo no cumple con la categoría y modalidad del torneo");
        }
        if (!equipos.contains(equipo)) {
            equipos.add(equipo);
        }else {
            throw new IllegalArgumentException("El equipo no puede inscribirse dos veces en un torneo");
        }
    }

	public void asignarPartidoAInstalacion(Partido partido, Instalacion instalacion, LocalTime inicio, LocalTime fin) {
    if (partido == null) {
        throw new IllegalArgumentException("El partido no puede ser null");
    }
    if (instalacion == null) {
        throw new IllegalArgumentException("La instalación no puede ser null");
    }
    if (!sede.getInstalaciones().contains(instalacion)) {
        throw new IllegalArgumentException("La instalación no pertenece a la sede del torneo");
    }
    if (!instalacion.esAdecuadaPara(deporte)) {
        throw new IllegalArgumentException("La instalación no es adecuada para el deporte del torneo: " + deporte);
    }
    instalacion.asignarPartido(partido, inicio, fin);
}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
	     if (nombre == null || nombre.trim().isEmpty()) {
	            throw new IllegalArgumentException("El nombre del torneo no puede ser null");
	        }
		this.nombre = nombre;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
}
