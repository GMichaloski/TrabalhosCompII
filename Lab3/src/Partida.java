public class Partida {
    int resultado;
    Jogador jogador1, jogador2;

    public Partida(Jogador P1, Jogador P2) {
        jogador1 = P1;
        jogador2 = P2;
        resultado = getResultado(P1, P2);
    }

    public static int getResultado(Jogador P1, Jogador P2) {
        if (P1.points > P2.points) {
            return 1;
        } else if (P1.points < P2.points) {
            return 2;
        } else {
            return 0;
        }
    }

    public void pontuar(Jogador vencedor, Jogador perdedor) {
        vencedor.points++;
        perdedor.points--;
    }

    public void encerrando(){
        //Para evitar repetições desnecessárias
        this.jogador1.partidas.add(this);
        this.jogador2.partidas.add(this);
        this.jogador1.setJogando(false);
        this.jogador2.setJogando(false);
    }
}
