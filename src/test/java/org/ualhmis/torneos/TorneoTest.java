package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

public class TorneoTest {

    @Test
    @DisplayName("Crear múltiples torneos los añade correctamente a la lista")
    void testCrearMultiplesTorneos() {
        GestorTorneos gestor = new GestorTorneos();

        Sede sede1 = new Sede("Polideportivo Central");
        Sede sede2 = new Sede("Estadio Municipal");

        gestor.crearTorneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede1);
        gestor.crearTorneo("Copa Senior", "Baloncesto", "Senior", "Femenino", "Copa", sede2);

        List<Torneo> torneos = gestor.getTorneos();
        assertEquals(2, torneos.size());
        assertEquals("Liga Juvenil", torneos.get(0).getNombre());
        assertEquals("Copa Senior", torneos.get(1).getNombre());
        assertEquals(sede1, torneos.get(0).getSede());
        assertEquals(sede2, torneos.get(1).getSede());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Constructor lanza excepción si el nombre es nulo o vacío")
    void testConstructorNombreInvalido(String nombreInvalido) {
        Sede sede = new Sede("Polideportivo Central");

        assertThrows(IllegalArgumentException.class, () -> 
            new Torneo(nombreInvalido, "Fútbol", "Juvenil", "Masculino", "Liga", sede)
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Registrar equipo lanza excepción si el equipo es null")
    void testRegistrarEquipoNull(Equipo equipo) {
        Sede sede = new Sede("Polideportivo Central");
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @Test
    @DisplayName("Registrar equipo lanza excepción si el equipo no cumple con la categoría o modalidad")
    void testRegistrarEquipoCategoriaModalidadIncorrecta() {
        Sede sede = new Sede("Polideportivo Central");
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo("Tigres", "Cadete", "Masculino", entrenador); // Categoría incorrecta

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @Test
    @DisplayName("Registrar equipo lanza excepción si el equipo ya está registrado")
    void testRegistrarEquipoDuplicado() {
        Sede sede = new Sede("Polideportivo Central");
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);

        torneo.registrarEquipo(equipo);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @Test
    @DisplayName("Registrar equipo válido lo añade correctamente a la lista")
    void testRegistrarEquipoValido() {
        Sede sede = new Sede("Polideportivo Central");
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);

        torneo.registrarEquipo(equipo);

        List<Equipo> equipos = torneo.getEquipos();
        assertEquals(1, equipos.size());
        assertTrue(equipos.contains(equipo));
    }

    @ParameterizedTest
    @CsvSource({
        "Liga Juvenil, Fútbol, Juvenil, Masculino, Liga",
        "Copa Absoluta, Baloncesto, Absoluta, Femenino, Copa"
    })
    @DisplayName("Getters y setters funcionan correctamente")
    void testGettersAndSetters(String nombre, String deporte, String categoria, String modalidad, String tipo) {
        Sede sede = new Sede("Polideportivo Central");
        Torneo torneo = new Torneo("Temp", "Temp", "Temp", "Temp", "Temp", sede);

        torneo.setNombre(nombre);
        torneo.setDeporte(deporte);
        torneo.setCategoria(categoria);
        torneo.setModalidad(modalidad);
        torneo.setTipo(tipo);

        assertEquals(nombre, torneo.getNombre());
        assertEquals(deporte, torneo.getDeporte());
        assertEquals(categoria, torneo.getCategoria());
        assertEquals(modalidad, torneo.getModalidad());
        assertEquals(tipo, torneo.getTipo());
    }

    @Test
    @DisplayName("SetEquipos actualiza correctamente la lista de equipos")
    void testSetEquipos() {
        Sede sede = new Sede("Polideportivo Central");
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Entrenador entrenador1 = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo1 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador1);

        Entrenador entrenador2 = new Entrenador("Luis", "Masculino", LocalDate.of(1985, 5, 15));
        Equipo equipo2 = new Equipo("Leones", "Juvenil", "Masculino", entrenador2);

        List<Equipo> nuevosEquipos = new ArrayList<>();
        nuevosEquipos.add(equipo1);
        nuevosEquipos.add(equipo2);

        torneo.setEquipos(nuevosEquipos);

        List<Equipo> equipos = torneo.getEquipos();
        assertEquals(2, equipos.size());
        assertTrue(equipos.contains(equipo1));
        assertTrue(equipos.contains(equipo2));
    }

    @Test
    @DisplayName("Asignar partido lanza excepción si los horarios se solapan")
    void testAsignarPartidoHorariosSolapados() {
        Sede sede = new Sede("Polideportivo Central");
        Instalacion instalacion = new Instalacion("Campo de Fútbol", TiposInstalacion.Campo, List.of("Fútbol"));
        sede.agregarInstalacion(instalacion);

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Equipo equipo1 = new Equipo("Tigres", "Juvenil", "Masculino", new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10)));
        Equipo equipo2 = new Equipo("Leones", "Juvenil", "Masculino", new Entrenador("Luis", "Masculino", LocalDate.of(1985, 5, 15)));

        Partido partido1 = new Partido(equipo1, equipo2);
        Partido partido2 = new Partido(equipo1, equipo2);

        torneo.asignarPartidoAInstalacion(partido1, instalacion, LocalTime.of(10, 0), LocalTime.of(12, 0));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            torneo.asignarPartidoAInstalacion(partido2, instalacion, LocalTime.of(11, 0), LocalTime.of(13, 0))
        );

        assertEquals("Horario solapado con otro partido", exception.getMessage());
    }
    @Test
    @DisplayName("Asignar partido a instalación válida lo asigna correctamente")
    void testAsignarPartidoAInstalacionValida() {
        Sede sede = new Sede("Polideportivo Central");
        Instalacion instalacion = new Instalacion("Campo de Fútbol", TiposInstalacion.Campo, List.of("Fútbol"));
        sede.agregarInstalacion(instalacion);

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Equipo equipo1 = new Equipo("Tigres", "Juvenil", "Masculino", new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10)));
        Equipo equipo2 = new Equipo("Leones", "Juvenil", "Masculino", new Entrenador("Luis", "Masculino", LocalDate.of(1985, 5, 15)));

        Partido partido = new Partido(equipo1, equipo2);

        assertDoesNotThrow(() -> torneo.asignarPartidoAInstalacion(partido, instalacion, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        assertEquals(instalacion, partido.getInstalacion());
    }

    @Test
    @DisplayName("Asignar partido lanza excepción si la instalación no es adecuada para el deporte")
    void testAsignarPartidoInstalacionNoAdecuada() {
        Sede sede = new Sede("Polideportivo Central");
        Instalacion instalacion = new Instalacion("Pista de Tenis", TiposInstalacion.Pista, List.of("Tenis"));
        sede.agregarInstalacion(instalacion);

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Equipo equipo1 = new Equipo("Tigres", "Juvenil", "Masculino", new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10)));
        Equipo equipo2 = new Equipo("Leones", "Juvenil", "Masculino", new Entrenador("Luis", "Masculino", LocalDate.of(1985, 5, 15)));

        Partido partido = new Partido(equipo1, equipo2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            torneo.asignarPartidoAInstalacion(partido, instalacion, LocalTime.of(10, 0), LocalTime.of(12, 0))
        );

        assertEquals("La instalación no es adecuada para el deporte del torneo: Fútbol", exception.getMessage());
    }
    
    @Test
    @DisplayName("Asignar partido lanza excepción si el partido es null")
    void testAsignarPartidoNull() {
        Sede sede = new Sede("Polideportivo Central");
        Instalacion instalacion = new Instalacion("Campo de Fútbol", TiposInstalacion.Campo, List.of("Fútbol"));
        sede.agregarInstalacion(instalacion);

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            torneo.asignarPartidoAInstalacion(null, instalacion, LocalTime.of(10, 0), LocalTime.of(12, 0))
        );

        assertEquals("El partido no puede ser null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Asignar partido lanza excepción si la instalación es null")
    void testAsignarPartidoInstalacionNull() {
        Sede sede = new Sede("Polideportivo Central");

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Equipo equipo1 = new Equipo("Tigres", "Juvenil", "Masculino", new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10)));
        Equipo equipo2 = new Equipo("Leones", "Juvenil", "Masculino", new Entrenador("Luis", "Masculino", LocalDate.of(1985, 5, 15)));

        Partido partido = new Partido(equipo1, equipo2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            torneo.asignarPartidoAInstalacion(partido, null, LocalTime.of(10, 0), LocalTime.of(12, 0))
        );

        assertEquals("La instalación no puede ser null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Asignar partido lanza excepción si la instalación no pertenece a la sede")
    void testAsignarPartidoInstalacionNoPertenece() {
        Sede sede = new Sede("Polideportivo Central");
        Instalacion instalacionNoPertenece = new Instalacion("Campo de Fútbol", TiposInstalacion.Campo, List.of("Fútbol"));

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sede);

        Equipo equipo1 = new Equipo("Tigres", "Juvenil", "Masculino", new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10)));
        Equipo equipo2 = new Equipo("Leones", "Juvenil", "Masculino", new Entrenador("Luis", "Masculino", LocalDate.of(1985, 5, 15)));

        Partido partido = new Partido(equipo1, equipo2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            torneo.asignarPartidoAInstalacion(partido, instalacionNoPertenece, LocalTime.of(10, 0), LocalTime.of(12, 0))
        );

        assertEquals("La instalación no pertenece a la sede del torneo", exception.getMessage());
    }
    
    @Test
    @DisplayName("SetSede actualiza correctamente la sede del torneo")
    void testSetSede() {
        Sede sedeInicial = new Sede("Polideportivo Central");
        Sede nuevaSede = new Sede("Estadio Municipal");

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sedeInicial);

        torneo.setSede(nuevaSede);

        assertEquals(nuevaSede, torneo.getSede());
    }

    @Test
    @DisplayName("SetSede lanza excepción si la sede es null")
    void testSetSedeNull() {
        Sede sedeInicial = new Sede("Polideportivo Central");

        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", sedeInicial);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> torneo.setSede(null));

        assertEquals("El torneo debe estar asociado a una sede específica", exception.getMessage());
    }
}