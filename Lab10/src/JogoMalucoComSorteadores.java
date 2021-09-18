

public class JogoMalucoComSorteadores <S1 extends Sorteador,S2 extends Sorteador> extends JogoDeDoisJogadores{

    S1 sorteador1;
    S2 sorteador2;
    public JogoMalucoComSorteadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas,
                                    S1 sorteador1, S2 sorteador2) {
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }


    @Override
    protected int executarRodadaDoJogo() {
        int sorteioJ1, sorteioJ2;
        sorteioJ1 = sorteador1.sortear();
        sorteioJ2 = sorteador2.sortear();
        if (sorteioJ1 > sorteioJ2) {
            return 1;
        } else if (sorteioJ2 > sorteioJ1) {
            return 2;
        } else {
            return 0;
        }
    }
}
