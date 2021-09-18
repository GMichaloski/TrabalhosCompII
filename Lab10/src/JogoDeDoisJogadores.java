import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

public abstract class JogoDeDoisJogadores {
    String nomeJogo, nomeJogador1, nomeJogador2;
    int numeroDeRodadas;
    final int VITORIA_JOGADOR_1 = 1, VITORIA_JOGADOR_2 = 2, EMPATE = 0;
    ArrayList<Partida> historicoResultados = new ArrayList<>();

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

    int setNumeroDeRodadas(int numeroDeRodadas) {
        this.numeroDeRodadas = numeroDeRodadas;
        return numeroDeRodadas;
    }

    protected abstract int executarRodadaDoJogo();

    String jogar() {
        final Date data = new Date();
        int scoreJ1 = 0, scoreJ2 = 0, empates = 0, resultadoRodada;
        for (int i = 0; i < numeroDeRodadas; i++) {
            resultadoRodada = executarRodadaDoJogo();
            if (resultadoRodada == 1) {
                scoreJ1++;
            } else if (resultadoRodada == 2) {
                scoreJ2++;
            } else {
                empates++;
            }
        }
        Partida partida = new Partida(data, scoreJ1, scoreJ2,empates);
        historicoResultados.add(partida);
        if (scoreJ1 > scoreJ2) {
            return "O jogador " + nomeJogador1 + " venceu o jogo por " + scoreJ1 + " a " + scoreJ2;
        } else if (scoreJ2 > scoreJ1) {
            return "O jogador " + nomeJogador2 + " venceu o jogo por " + scoreJ2 + " a " + scoreJ1;
        } else {
            return "O jogo terminou em empate após " + numeroDeRodadas + " rodadas";
        }

    }
    public String obterResultadoUltimoJogo() {

        if (this.historicoResultados.isEmpty()) {
            return null;
        }

        Partida ultimaPartidaJogada = this.historicoResultados.get(
                this.historicoResultados.size() - 1);

        int contRodadasVencidasJogador1 = ultimaPartidaJogada.contRodadasVencidasJogador1;
        int contRodadasVencidasJogador2 = ultimaPartidaJogada.contRodadasVencidasJogador2;

        if (contRodadasVencidasJogador1 == contRodadasVencidasJogador2) {
            return String.format("O jogo terminou em empate após %d rodadas.",
                    this.numeroDeRodadas);
        } else {

            String nomeVencedor;
            int pontosVencedor;
            int pontosPerdedor;

            if (contRodadasVencidasJogador1 > contRodadasVencidasJogador2) {
                nomeVencedor = this.nomeJogador1;
                pontosVencedor = contRodadasVencidasJogador1;
                pontosPerdedor = contRodadasVencidasJogador2;
            } else {
                nomeVencedor = this.nomeJogador2;
                pontosVencedor = contRodadasVencidasJogador2;
                pontosPerdedor = contRodadasVencidasJogador1;
            }

            return String.format("O jogador %s venceu o jogo por %d a %d",
                    nomeVencedor, pontosVencedor, pontosPerdedor);
        }
    }
    public float getPercentualVitoriasJogador1() {
        float scoreJ1 = 0;
        for(Partida i: historicoResultados){
            if(i.contRodadasVencidasJogador1 > i.contRodadasVencidasJogador2){
                scoreJ1++;
            }
        }
        return (scoreJ1/historicoResultados.size()*100);
    }

    /**
     * Returna um float entre 0 e 100 indicando o percentual
     * de vitórias do Jogador 2 em todos os tempos.
     *
     * @return o percentual, como um float entre 0 (0%) e 100 (100%)
     */
    public float getPercentualVitoriasJogador2() {
        float scoreJ2 = 0;
        for(Partida i: historicoResultados){
            if(i.contRodadasVencidasJogador1 < i.contRodadasVencidasJogador2){
                scoreJ2++;
            }
        }
        return (scoreJ2/historicoResultados.size()*100);
    }

    /**
     * Returna um float entre 0 e 100 indicando o percentual
     * de empates em todos os tempos.
     *
     * @return o percentual, como um float entre 0 (0%) e 100 (100%)
     */
    public float getPercentualEmpates() {
        float empates = 0;
        for(Partida i: historicoResultados){
            if(i.contRodadasVencidasJogador1 == i.contRodadasVencidasJogador2){
                empates++;
            }
        }
        return (empates/historicoResultados.size()*100);
    }

    private class Partida {

        final Date data;
        final int contRodadasVencidasJogador1;
        final int contRodadasVencidasJogador2;
        final int contEmpates;

        Partida(Date data,
                int contRodadasVencidasJogador1,
                int contRodadasVencidasJogador2,
                int contEmpates) {
            this.data = data;
            this.contRodadasVencidasJogador1 = contRodadasVencidasJogador1;
            this.contRodadasVencidasJogador2 = contRodadasVencidasJogador2;
            this.contEmpates = contEmpates;
        }

        int getResultado() {
            // retorno o resultado desta partida
            if (contRodadasVencidasJogador1 > contRodadasVencidasJogador2) {
                return VITORIA_JOGADOR_1;
            } else if (contRodadasVencidasJogador1 < contRodadasVencidasJogador2) {
                return VITORIA_JOGADOR_2;
            }
            return EMPATE;
        }
    }
}

