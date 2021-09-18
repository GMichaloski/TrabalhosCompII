import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        Sorteador sorteadorJ1 = new DadosDeGamao();
        Sorteador sorteadorJ2 = new DadosTriplos();


        JogoMalucoComSorteadores JM = new JogoMalucoComSorteadores("JogoMuitoMaluco",
                "Guga", "Vini", 1,
                sorteadorJ1, sorteadorJ2);
        for (int numeroDeRodadas = 1; numeroDeRodadas <= 100000; numeroDeRodadas *= 2) {
            JM.setNumeroDeRodadas(numeroDeRodadas);
            JM.jogar();

            System.out.println(JM.obterResultadoUltimoJogo());
        }
        System.out.println("Porcentagem de vitórias do Jogador 1: " + JM.getPercentualVitoriasJogador1());
        System.out.println("Porcentagem de vitórias do Jogador 2: " + JM.getPercentualVitoriasJogador2());
        System.out.println("Porcentagem de empates: " + JM.getPercentualEmpates());
    }
}