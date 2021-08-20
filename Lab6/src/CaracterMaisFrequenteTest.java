import org.junit.Test;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {

    @Test
    public void testarCaracterMaisFrequente() {
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequenteL("arara"));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequenteL("a r a r a 345 sdf hhh"));

    }
}