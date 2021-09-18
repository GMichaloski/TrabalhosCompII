public class DadosTriplos implements Sorteador{
    @Override
    public int sortear() {
        Dado rolagem = new Dado();
        int a = rolagem.sortear(), b = rolagem.sortear(), c = rolagem.sortear();
        return a+b+c;
    }
}
