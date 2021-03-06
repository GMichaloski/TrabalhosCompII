import java.util.ArrayList;

public class JogoOnline {

    public static ArrayList<Jogador> jogadores = new ArrayList<>();

    public static boolean CadastroJogadores(String name, String password) {
        Jogador newPlayer = new Jogador(name, password);
        for (Jogador i : jogadores) {
            if (i.userName.equals(newPlayer.userName)) {
                return false;
            }
        }
        jogadores.add(newPlayer);
        newPlayer.points = newPlayer.getINITIAL_POINTS();
        return true;
    }

    public static void Login(String userName, String userPassword) {

        Jogador i = getJogador(userName);
        if (!i.getOnline()) {
            if (i.userPassword.equals(userPassword)) {
                i.setOnline(true);
            }
        }
    }

    public static void Logout(String userName) {

        Jogador i = getJogador(userName);
        if (i.getOnline()) {
            i.setOnline(false);
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

        if (P1.getOnline() && P2.getOnline() && !P1.getJogando() && !P2.getJogando()) {
            P1.setOnline(true);
            P2.setOnline(true);
            Partida partida = new Partida(P1, P2);
            return partida;
        }
        return null;
        //ToDo resolver nullPointerException
    }

    public static Jogador escolherAdversario(Jogador solicitante) {
        for (Jogador i : jogadores) {
            if (i != solicitante && i.getOnline() && !i.getJogando()) {
                return i;
            }
        }
        return null;
        //ToDo resolver nullPointerException
    }

    public static void encerrarPartida(Partida partida) {
        int resultado = partida.resultado;
        if (resultado == 1) {
            partida.pontuar(partida.jogador1, partida.jogador2);
            partida.encerrando();

        } else if (resultado == 2) {
            partida.pontuar(partida.jogador2, partida.jogador1);
            partida.encerrando();
        } else {
            partida.encerrando();
        }
    }

}
