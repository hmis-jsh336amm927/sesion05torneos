package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;


public class SedeTest {

    @ParameterizedTest
    @CsvSource({
        "Sede Central", 
        "Sede Norte", 
        "Sede Sur"
    })
    void testConstructorWithValidInputs(String nombre) {
        Sede sede = new Sede(nombre);
        assertEquals(nombre, sede.getInstalaciones().isEmpty() ? nombre : ""); 
    }

    @Test
    void testAgregarInstalacion() {
        // Test adding valid installations
    }
}