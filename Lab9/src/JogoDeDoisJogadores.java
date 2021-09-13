import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {
    String nomeJogo, nomeJogador1, nomeJogador2;
    int numeroDeRodadas;
    ArrayList<Integer> historicoResultados;

    public JogoDeDoisJogadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas) {
        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
    }

    String getNomeJogo() {
        return nomeJogo;
    }

    String getNomeJogador1() {
        return nomeJogador1;
    }

    String getNomeJogador2() {
        return nomeJogador2;
    }

    int getNumeroDeRodadas() {
        return numeroDeRodadas;
    }


    protected abstract int executarRodadaDoJogo();

    String jogar() {
        int scoreJ1 = 0, scoreJ2 = 0, resultadoRodada;
        for (int i = 0; i < numeroDeRodadas; i++) {
            resultadoRodada = executarRodadaDoJogo();
            if (resultadoRodada == 1){
                scoreJ1++;
            }
            else if (resultadoRodada == 2){
                scoreJ2++;
            }
        }
        if (scoreJ1 > scoreJ2) {
            return "O jogador " + nomeJogador1 + " venceu o jogo por " + scoreJ1 + " a " + scoreJ2;
        } else if (scoreJ2 > scoreJ1) {
            return "O jogador " + nomeJogador2 + " venceu o jogo por " + scoreJ2 + " a " + scoreJ1;
        } else {
            return "O jogo terminou em empate após " + numeroDeRodadas + " rodadas";
        }

    }
}
