import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SomaDoParTest {

    private ArrayList<Integer> lista;
    long inicio, duracao;

    @Before
    public void setUp() {


        int[] array = new int[] {1, 45, -8, 50, 12, 900, -7, 4, 49};
        lista = new ArrayList<>();
        // I definido com -901 pra não interferir nos já estabelecidos testes
        for(int i = -901; i > -100000; i--){
            lista.add(i);
        }
        for (int elemento : array) {
            lista.add(elemento);
        }
    }

    @Test
    public void testarSomaDoParQuandoEncontra() {

        inicio = System.currentTimeMillis();
        assertEquals(Integer.valueOf(-8), SomaDoPar.encontrarParComSomaDadaL(lista, 4));
        assertEquals(Integer.valueOf(4), SomaDoPar.encontrarParComSomaDadaL(lista, 53));
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Tempo de execução do método linear: %.10f\n",duracao/1000f);
        inicio = System.currentTimeMillis();
        assertEquals(Integer.valueOf(-8), SomaDoPar.encontrarParComSomaDadaQ(lista, 4));
        assertEquals(Integer.valueOf(4), SomaDoPar.encontrarParComSomaDadaQ(lista, 53));
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Tempo de execução do método quadrática: %.10f", duracao/1000f);
    }

    @Test
    public void testarSomaDoParQuandoNaoEncontra() {
        assertNull(SomaDoPar.encontrarParComSomaDadaL(lista, 1000000));
        assertNull(SomaDoPar.encontrarParComSomaDadaL(lista, 1));
        assertNull(SomaDoPar.encontrarParComSomaDadaL(lista, 0));
    }

}