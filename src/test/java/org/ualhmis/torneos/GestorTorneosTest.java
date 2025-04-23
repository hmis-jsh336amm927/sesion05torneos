package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class GestorTorneosTest {

    @Test
    @DisplayName("Constructor inicializa correctamente la lista de torneos")
    void testConstructor() {
        GestorTorneos gestor = new GestorTorneos();
        assertNotNull(gestor.getTorneos());
        assertTrue(gestor.getTorneos().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "Liga Juvenil, Fútbol, Juvenil, Masculino, Liga",
        "Copa Senior, Baloncesto, Senior, Femenino, Copa"
    })
    @DisplayName("Crear torneo añade correctamente un torneo a la lista")
    void testCrearTorneo(String nombre, String deporte, String categoria, String modalidad, String tipo) {
        GestorTorneos gestor = new GestorTorneos();

        gestor.crearTorneo(nombre, deporte, categoria, modalidad, tipo);

        List<Torneo> torneos = gestor.getTorneos();
        assertEquals(1, torneos.size());
        Torneo torneo = torneos.get(0);
        assertEquals(nombre, torneo.getNombre());
        assertEquals(deporte, torneo.getDeporte());
        assertEquals(categoria, torneo.getCategoria());
        assertEquals(modalidad, torneo.getModalidad());
        assertEquals(tipo, torneo.getTipo());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Crear torneo lanza excepción si el nombre es nulo o vacío")
    void testCrearTorneoNombreNuloOVacio(String nombreInvalido) {
        GestorTorneos gestor = new GestorTorneos();

        assertThrows(IllegalArgumentException.class, () -> gestor.crearTorneo(nombreInvalido, "Fútbol", "Juvenil", "Masculino", "Liga"));
    }

    @Test
    @DisplayName("Crear torneo lanza excepción si el nombre contiene solo espacios en blanco")
    void testCrearTorneoNombreSoloEspacios() {
        GestorTorneos gestor = new GestorTorneos();

        assertThrows(IllegalArgumentException.class, () -> gestor.crearTorneo("   ", "Fútbol", "Juvenil", "Masculino", "Liga"));
    }

    @Test
    @DisplayName("Crear múltiples torneos los añade correctamente a la lista")
    void testCrearMultiplesTorneos() {
        GestorTorneos gestor = new GestorTorneos();

        gestor.crearTorneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");
        gestor.crearTorneo("Copa Senior", "Baloncesto", "Senior", "Femenino", "Copa");

        List<Torneo> torneos = gestor.getTorneos();
        assertEquals(2, torneos.size());
        assertEquals("Liga Juvenil", torneos.get(0).getNombre());
        assertEquals("Copa Senior", torneos.get(1).getNombre());
    }
}