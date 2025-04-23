package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
    @DisplayName("Constructor inicializa correctamente los valores")
    void testConstructor() {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

        assertEquals("Liga Juvenil", torneo.getNombre());
        assertEquals("Fútbol", torneo.getDeporte());
        assertEquals("Juvenil", torneo.getCategoria());
        assertEquals("Masculino", torneo.getModalidad());
        assertEquals("Liga", torneo.getTipo());
        assertNotNull(torneo.getEquipos());
        assertTrue(torneo.getEquipos().isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Constructor lanza excepción si el nombre es nulo o vacío")
    void testConstructorNombreInvalido(String nombreInvalido) {
        assertThrows(IllegalArgumentException.class, () -> new Torneo(nombreInvalido, "Fútbol", "Juvenil", "Masculino", "Liga"));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Registrar equipo lanza excepción si el equipo es null")
    void testRegistrarEquipoNull(Equipo equipo) {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @Test
    @DisplayName("Registrar equipo lanza excepción si el equipo no cumple con la categoría o modalidad")
    void testRegistrarEquipoCategoriaModalidadIncorrecta() {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo("Tigres", "Cadete", "Masculino", entrenador); // Categoría incorrecta

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @Test
    @DisplayName("Registrar equipo lanza excepción si el equipo ya está registrado")
    void testRegistrarEquipoDuplicado() {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);

        torneo.registrarEquipo(equipo);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @Test
    @DisplayName("Registrar equipo válido lo añade correctamente a la lista")
    void testRegistrarEquipoValido() {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

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
        "Copa Senior, Baloncesto, Senior, Femenino, Copa"
    })
    @DisplayName("Getters y setters funcionan correctamente")
    void testGettersAndSetters(String nombre, String deporte, String categoria, String modalidad, String tipo) {
        Torneo torneo = new Torneo("Temp", "Temp", "Temp", "Temp", "Temp");

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
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

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
}