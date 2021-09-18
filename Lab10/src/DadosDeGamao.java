public class DadosDeGamao implements Sorteador {
    @Override
    public int sortear() {
        Dado rolagem = new Dado();
        int a = rolagem.sortear(), b = rolagem.sortear(), c;
        if (a == b){
            c = 2*(a+b);
        }
        else{
            c = a+b;
        }
        return c;
    }
}
