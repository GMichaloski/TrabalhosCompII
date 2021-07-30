import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JogoOnlineTest {
    private Jogador Guga, Vini, Millo;
    @Before
    public void setUp() {

        JogoOnline.CadastroJogadores("Guga","Vasco");
        JogoOnline.CadastroJogadores("Vini", "Millo");
        JogoOnline.CadastroJogadores("Guga","Senha");
        JogoOnline.CadastroJogadores("Millo", "Gremio");
        Millo = JogoOnline.getJogador("Millo");
        Guga = JogoOnline.getJogador("Guga");
        Vini = JogoOnline.getJogador("Vini");
    }

    @Test
    public void testarUsuarioRepetido() {
        assertEquals("Não se pode cadastrar contas com o mesmo usuário", false ,
          JogoOnline.CadastroJogadores("Guga","Vasco"));
    }

    @Test
    public void testarCadastros(){

        assertEquals("As contas cadastradas devem ser salvas", 3, JogoOnline.jogadores.size());

    }

    @Test
    public void testarOffline(){
        JogoOnline.Logout("Guga");
        assertEquals("Se não estiver logado, o usuário deve estar offline",
                false, JogoOnline.jogadores.get(0).online);
    }


    @Test
    public void testarLogin(){
        JogoOnline.Login("Guga", "Vasco");
        assertEquals("Com usuário e senha corretos, o usuário deve ser capaz de logar em sua conta", true,
                JogoOnline.jogadores.get(0).online);
    }

    @Test
    public void testarFalhaLogin(){
        JogoOnline.Logout("Vini");
        JogoOnline.Login("Vini", "Botafogo");
        assertEquals("Com usuário e senha incorretos, o usuário não deve ser capaz de logar em sua conta",
                false, JogoOnline.jogadores.get(1).online);
    }

    @Test
    public void testarLogout(){
        JogoOnline.Login("Vini", "Millo");
        JogoOnline.Logout("Vini");

        assertEquals("Após logado, o usuário deve ter a opção de deslogar", false,
                JogoOnline.jogadores.get(1).online);
    }

    @Test
    public void testarGetJogador(){
        Jogador teste = JogoOnline.getJogador("Vini");
        assertEquals("A função deve retornar o Jogador q possui tal usuário", JogoOnline.jogadores.get(1), teste);
    }

    @Test
    public void testarVencedor1(){

        Guga.points = 1200;
        Vini.points = 900;
        Guga.online = true;
        Vini.online = true;
        Guga.Jogando = false;
        Vini.Jogando = false;

        Partida partida = JogoOnline.iniciarPartida(Guga,Vini);
        assertEquals("O primeiro jogador ganha se obtiver a maior pontuação", 1, partida.resultado);
    }

    @Test
    public void testarVencedor2(){

        Guga.points = 800;
        Vini.points = 900;
        Guga.online = true;
        Vini.online = true;
        Guga.Jogando = false;
        Vini.Jogando = false;
        Partida partida = JogoOnline.iniciarPartida(Guga,Vini);
        assertEquals("O segundo jogador ganha se obtiver a maior pontuação", 2, partida.resultado);
    }

    @Test
    public void testarEmpate(){

        Guga.points = 1000;
        Vini.points = 1000;
        Guga.online = true;
        Vini.online = true;
        Guga.Jogando = false;
        Vini.Jogando = false;
        Partida partida = JogoOnline.iniciarPartida(Guga,Vini);
        assertEquals("Se a pontuação de ambos for igual, teremos um empate", 0, partida.resultado);
    }

    @Test
    public void testarAdversario(){
        JogoOnline.Login("Millo","Gremio");
        Guga.online = true;
        Vini.online = true;
        Guga.Jogando = false;
        Vini.Jogando = true;
        assertEquals("O usuário encontrando deve ser diferente do solicitante, estar online e disponivel",
                Millo, JogoOnline.escolherAdversario(Guga));

    }

    @Test
    public void testarFinal(){
        JogoOnline.CadastroJogadores("Ana", "Vasco");
        JogoOnline.CadastroJogadores("Bia", "Bola" );
        JogoOnline.Login("Ana", "Vasco");
        JogoOnline.Login("Bia", "Bola");
        JogoOnline.getJogador("Ana").points = 1001;
        Partida ultimaPartida = JogoOnline.iniciarPartida(JogoOnline.getJogador("Ana"),
                JogoOnline.getJogador("Bia"));
        JogoOnline.encerrarPartida(ultimaPartida);
        assertEquals("É preciso definir o vencedor",1002,
                JogoOnline.getJogador("Ana").points);
        assertEquals("É preciso registrar",1, JogoOnline.getJogador("Ana").partidas.size());
        assertEquals("É preciso pontuar corretamente",999,
                JogoOnline.getJogador("Bia").points);
    }
    /*for (int i = 0; i < JogoOnline.jogadores.size();i++)
        {
            System.out.println(JogoOnline.jogadores.get(i).userName);
        }*/
}
