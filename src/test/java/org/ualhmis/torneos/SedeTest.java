package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class SedeTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Constructor lanza excepción si el nombre es nulo o vacío")
    void testConstructorNombreInvalido(String nombreInvalido) {
        assertThrows(IllegalArgumentException.class, () -> new Sede(nombreInvalido));
    }

    @Test
    @DisplayName("Constructor inicializa correctamente el nombre y la lista de instalaciones")
    void testConstructorValido() {
        Sede sede = new Sede("Sede1");
        assertEquals("Sede1", sede.getNombre());
        assertNotNull(sede.getInstalaciones());
        assertTrue(sede.getInstalaciones().isEmpty());
    }

    @Test
    @DisplayName("Agregar instalación la añade correctamente a la lista")
    void testAgregarInstalacion() {
        Sede sede = new Sede("Sede1");
        Instalacion instalacion = new Instalacion("Instalacion1", TiposInstalacion.Campo, List.of("Futbol"));

        sede.agregarInstalacion(instalacion);

        assertEquals(1, sede.getInstalaciones().size());
        assertTrue(sede.getInstalaciones().contains(instalacion));
    }

    @Test
    @DisplayName("Obtener instalaciones devuelve la lista correcta")
    void testGetInstalaciones() {
        Sede sede = new Sede("Sede1");
        Instalacion instalacion1 = new Instalacion("Instalacion1", TiposInstalacion.Campo, List.of("Futbol"));
        Instalacion instalacion2 = new Instalacion("Instalacion2", TiposInstalacion.Pista, List.of("Baloncesto"));

        sede.agregarInstalacion(instalacion1);
        sede.agregarInstalacion(instalacion2);

        List<Instalacion> instalaciones = sede.getInstalaciones();

        assertEquals(2, instalaciones.size());
        assertTrue(instalaciones.contains(instalacion1));
        assertTrue(instalaciones.contains(instalacion2));
    }

    @Test
    @DisplayName("SetNombre actualiza correctamente el nombre")
    void testSetNombre() {
        Sede sede = new Sede("Sede1");
        sede.setNombre("NuevaSede");

        assertEquals("NuevaSede", sede.getNombre());
    }

    @Test
    @DisplayName("SetInstalaciones actualiza correctamente la lista de instalaciones")
    void testSetInstalaciones() {
        Sede sede = new Sede("Sede1");

        Instalacion instalacion1 = new Instalacion("Instalacion1", TiposInstalacion.Campo, List.of("Futbol"));
        Instalacion instalacion2 = new Instalacion("Instalacion2", TiposInstalacion.Pista, List.of("Baloncesto"));

        List<Instalacion> nuevasInstalaciones = new ArrayList<>();
        nuevasInstalaciones.add(instalacion1);
        nuevasInstalaciones.add(instalacion2);

        sede.setInstalaciones(nuevasInstalaciones);

        assertEquals(2, sede.getInstalaciones().size());
        assertTrue(sede.getInstalaciones().contains(instalacion1));
        assertTrue(sede.getInstalaciones().contains(instalacion2));
    }

    @Test
    @DisplayName("Agregar instalación nula lanza excepción")
    void testAgregarInstalacionNula() {
        Sede sede = new Sede("Sede1");

        assertThrows(IllegalArgumentException.class, () -> sede.agregarInstalacion(null));
    }
}