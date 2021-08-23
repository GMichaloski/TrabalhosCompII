import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {
    private String textoTeste = " ";
    private long inicio,duracao;
    @Before
    public void setUp() {
        for (int i = 0; i < 10000; i++) {
            textoTeste += "abcdefghijklmnopqrstuvwxyz";
        }
    }


    @Test
    @Ignore
    public void testarCaracterMaisFrequenteL() {
        inicio = System.currentTimeMillis();
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequenteL("arara"+textoTeste));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequenteL("a r a r a 345 sdf hhh"));
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Tempo de execução do método linear: %.10f\n",duracao/1000f);

        inicio = System.currentTimeMillis();
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequenteQ("arara"+textoTeste));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequenteQ("a r a r a 345 sdf hhh"));
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Tempo de execução do método linear: %.10f\n",duracao/1000f);
    }
}