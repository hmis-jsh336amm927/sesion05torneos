package org.ualhmis.torneos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Instalacion{
    private String nombre;
    private TiposInstalacion subtipo;
    private List<String> deportesPermitidos;
    private List<Horario> horariosOcupados = new ArrayList<>();

    public Instalacion(String nombre, TiposInstalacion subtipo, List<String> deportesPermitidos) {
		if(nombre == null || subtipo == null || deportesPermitidos == null) throw new IllegalArgumentException();
        this.setNombre(nombre);
        this.setSubtipo(subtipo);
        this.deportesPermitidos = deportesPermitidos;
    }

    public boolean esAdecuadaPara(String deporte) {
        return deportesPermitidos.contains(deporte);
    }
    
    public void asignarPartido(Partido partido, LocalTime inicio, LocalTime fin) {
        for (Horario ocupado : horariosOcupados) {
            if (ocupado.seSolapaCon(inicio, fin)) {
                throw new IllegalArgumentException("Horario solapado con otro partido");
            }
        }
        horariosOcupados.add(new Horario(inicio, fin));
        partido.setInstalacion(this);
    }

	public TiposInstalacion getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(TiposInstalacion subtipo) {
		this.subtipo = subtipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Instalacion [nombre=" + nombre + ", subtipo=" + subtipo + ", deportesPermitidos=" + deportesPermitidos
				+ ", horariosOcupados=" + horariosOcupados + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(deportesPermitidos, horariosOcupados, nombre, subtipo);
	}

	@Override
	public boolean equals(Object obj) {
		Instalacion other = (Instalacion) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
