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
    
        Sede sede = new Sede("Polideportivo Central");
    
        gestor.crearTorneo(nombre, deporte, categoria, modalidad, tipo, sede);
    
        List<Torneo> torneos = gestor.getTorneos();
        assertEquals(1, torneos.size());
        Torneo torneo = torneos.get(0);
        assertEquals(nombre, torneo.getNombre());
        assertEquals(deporte, torneo.getDeporte());
        assertEquals(categoria, torneo.getCategoria());
        assertEquals(modalidad, torneo.getModalidad());
        assertEquals(tipo, torneo.getTipo());
        assertEquals(sede, torneo.getSede());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Crear torneo lanza excepción si el nombre es nulo o vacío")
    void testCrearTorneoNombreNuloOVacio(String nombreInvalido) {
        GestorTorneos gestor = new GestorTorneos();
    
        Sede sede = new Sede("Polideportivo Central");
    
        assertThrows(IllegalArgumentException.class, () -> 
            gestor.crearTorneo(nombreInvalido, "Fútbol", "Juvenil", "Masculino", "Liga", sede)
        );
    }

    @Test
    @DisplayName("Crear torneo lanza excepción si el nombre contiene solo espacios en blanco")
    void testCrearTorneoNombreSoloEspacios() {
        GestorTorneos gestor = new GestorTorneos();
    
        Sede sede = new Sede("Polideportivo Central");
    
        assertThrows(IllegalArgumentException.class, () -> 
            gestor.crearTorneo("   ", "Fútbol", "Juvenil", "Masculino", "Liga", sede)
        );
    }

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
    
    @Test
    @DisplayName("Crear torneo lanza excepción si la sede es null")
    void testCrearTorneoSedeNull() {
        GestorTorneos gestor = new GestorTorneos();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            gestor.crearTorneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga", null)
        );
        assertEquals("La sede no puede ser nula.", exception.getMessage());
    }
}