package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

// Restricciones en los equipos (jugadores de la misma categoría y modalidad)

class JugadorTest {

    @Test
    void testCategoriaPorEdad() {
        Jugador jugador1 = new Jugador("Carlos", "Masculino", LocalDate.of(2015, 5, 10));
        assertEquals("Infantil", jugador1.getCategoria());

        Jugador jugador2 = new Jugador("Luis", "Masculino", LocalDate.of(2011, 3, 15));
        assertEquals("Cadete", jugador2.getCategoria());

        Jugador jugador3 = new Jugador("Ana", "Femenino", LocalDate.of(2008, 8, 22));
        assertEquals("Juvenil", jugador3.getCategoria());

        Jugador jugador4 = new Jugador("Pedro", "Masculino", LocalDate.of(2005, 1, 30));
        assertEquals("Junior", jugador4.getCategoria());

        Jugador jugador5 = new Jugador("Marta", "Femenino", LocalDate.of(1998, 6, 5));
        assertEquals("Absoluta", jugador5.getCategoria());
    }

    @ParameterizedTest
    @CsvSource({
        "'', Masculino, 2010-01-01, El nombre no puede estar vacío",
        "null, Masculino, 2010-01-01, El nombre no puede estar vacío",
        "Juan, '', 2010-01-01, El género no puede estar vacío",
        "Juan, null, 2010-01-01, El género no puede estar vacío",
        "Juan, Masculino, null, La fecha de nacimiento no puede ser nula",
        "Juan, '   ', 2010-01-01, El género no puede estar vacío"
    })
    void testCreacionJugadorInvalido(String nombre, String genero, String fechaNacimiento, String mensajeEsperado) {
        LocalDate fecha = "null".equals(fechaNacimiento) ? null : LocalDate.parse(fechaNacimiento);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Jugador("null".equals(nombre) ? null : nombre, 
                              "null".equals(genero) ? null : genero, 
                              fecha));

        assertEquals(mensajeEsperado, exception.getMessage(), "El mensaje de la excepción no coincide");
    }
}
