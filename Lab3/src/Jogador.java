
import java.util.ArrayList;

public class Jogador {
    public String userName, userPassword;
    public ArrayList<Partida> partidas = new ArrayList<>();
    private final int INITIAL_POINTS = 1000;
    public int points = INITIAL_POINTS;
    public boolean online, Jogando;

    public Jogador(String name, String password){
        setUser(name);
        setUserPassword(password);
    }

    public void setUser(String name){
        this.userName = name;

    }
    public void setUserPassword(String password){
        this.userPassword = password;
    }

}
