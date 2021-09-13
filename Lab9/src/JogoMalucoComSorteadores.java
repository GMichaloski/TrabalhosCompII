public class JogoMalucoComSorteadores extends JogoDeDoisJogadores {
    Sorteador sorteador1, sorteador2;

    public JogoMalucoComSorteadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas,
                                    Sorteador s1, Sorteador s2) {
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        sorteador1 = s1;
        sorteador2 = s2;
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

    public static void main(String[] args) {
        Sorteador sorteadorJ1 = new DadosDeGamao();
        Sorteador sorteadorJ2 = new DadosTriplos();
        JogoMalucoComSorteadores JM = new JogoMalucoComSorteadores("JogoMuitoMaluco", "Guga", "Vini", 10000,
                sorteadorJ1, sorteadorJ2);
        System.out.println(JM.jogar());

    }


    // O sorteador que garante os maiores resultado é o dos Dados Triplos, por uma questão de probabilidade. Desde antes
    // de rodar o programa esse fato já era evidente, visto que, normalmente, os dados triplos obtém resultados maiores.
}
