package org.ualhmis.torneos;

import java.time.LocalTime;

public class Horario {
	private LocalTime inicio;
	private LocalTime fin;

	public Horario(LocalTime inicio, LocalTime fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	public boolean seSolapaCon(LocalTime otroInicio, LocalTime otroFin) {
	    return otroInicio.isBefore(fin) && otroFin.isAfter(inicio);
	}

}
