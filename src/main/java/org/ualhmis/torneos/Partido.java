package org.ualhmis.torneos;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private Instalacion instalacion;

    public Partido(Equipo equipo1, Equipo equipo2) {
        setEquipo1(equipo1);
        setEquipo2(equipo2);
    }

    public void registrarResultado(int golesEquipo1, int golesEquipo2) {
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
        if (equipo1 == null) {
            throw new IllegalArgumentException("El equipo de la sede no puede ser nulo o vacío.");
        }
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
        if (equipo2 == null) {
            throw new IllegalArgumentException("El equipo de la sede no puede ser nulo o vacío.");
        }
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}
    
}
