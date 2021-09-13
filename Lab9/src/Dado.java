import java.util.Random;

public class Dado implements Sorteador{

    @Override
    public int sortear() {
        Random numero = new Random();
        return numero.nextInt(6);
    }
}
