import java.util.ArrayList;

public class JogoOnline {

    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    //ToDO Get e set no Jogador

    public static boolean CadastroJogadores(String name, String password) {
        int INICIAL_POINTS = 1000;
        Jogador newPlayer = new Jogador(name, password);
        for (Jogador i : jogadores) {
            if (i.userName.equals(newPlayer.userName)) {
                return false;
            }
        }
        jogadores.add(newPlayer);
        newPlayer.points = INICIAL_POINTS;
        return true;
    }

    public static void Login(String userName, String userPassword) {

        Jogador i = getJogador(userName);
        if (!i.online) {
            if (i.userPassword.equals(userPassword)) {
                i.online = true;
            }
        }
    }

    public static void Logout(String userName) {

        Jogador i = getJogador(userName);
        if (i.online) {
            i.online = false;
        }

    }

    public static Jogador getJogador(String username) {

        //Método auxiliar utilizado para evitar repetição de código
        for (Jogador i : jogadores) {
            if (i.userName.equals(username)) {
                return i;
            }
        }
        return null;
    }

    public static Partida iniciarPartida(Jogador P1, Jogador P2) {

        if (P1.online && P2.online && !P1.Jogando && !P2.Jogando) {
            P1.Jogando = true;
            P2.Jogando = true;
            Partida partida = new Partida(P1, P2);
            return partida;
        }
        return null;
        //ToDo tirar dúvida
    }

    public static Jogador escolherAdversario(Jogador solicitante) {
        for (Jogador i : jogadores) {
            if (i != solicitante && i.online && !i.Jogando) {
                return i;
            }
        }
        return null;
        //ToDo tirar dúvida
    }

    public static void encerrarPartida(Partida partida){
        int resultado = partida.resultado;
        if(resultado == 1){
            partida.end(partida.jogador1,partida.jogador2);
            partida.jogador1.partidas.add(partida);
            partida.jogador2.partidas.add(partida);
            partida.jogador1.Jogando = false;
            partida.jogador2.Jogando = false;

        }
        else if(resultado == 2){
            partida.end(partida.jogador2,partida.jogador1);
            partida.jogador1.partidas.add(partida);
            partida.jogador2.partidas.add(partida);
            partida.jogador1.Jogando = false;
            partida.jogador2.Jogando = false;
        }
        else {
            partida.jogador1.partidas.add(partida);
            partida.jogador2.partidas.add(partida);
            partida.jogador1.Jogando = false;
            partida.jogador2.Jogando = false;
        }
    }
}
