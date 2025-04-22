package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;


public class PersonaTest {

    @ParameterizedTest
    @CsvSource({
        "Juan, Masculino, 1990-05-15",
        "Maria, Femenino, 1985-10-20",
        "Alex, No Binario, 2000-01-01"
    })
    void testConstructor(String nombre, String genero, String fechaNacimiento) {
        LocalDate fecha = LocalDate.parse(fechaNacimiento);
        Persona persona = new Persona(nombre, genero, fecha);

        assertEquals(nombre, persona.getNombre());
        assertEquals(genero, persona.getGenero());
        assertEquals(fecha, persona.getFechaNacimiento());
    }

    @Test
    void testCalcularEdad() {
        Persona persona = new Persona("Carlos", "Masculino", LocalDate.of(1990, 5, 15));
        int expectedAge = LocalDate.now().getYear() - 1990;
        assertEquals(expectedAge, persona.calcularEdad());
    }

    @ParameterizedTest
    @CsvSource({
        "2000-01-01, 25", 
        "1980-12-31, 45"  
    })
    void testCalcularEdadConDiferenteEdad(String fechaNacimiento, int expectedAge) {
        LocalDate fecha = LocalDate.parse(fechaNacimiento);
        Persona persona = new Persona("Test", "Masculino", fecha);
        assertEquals(expectedAge, persona.calcularEdad());
    }
}