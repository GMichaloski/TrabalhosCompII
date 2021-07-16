import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Trabalho1 {

    static Random meuGerador = new Random();

    public static int sortearInt(int menor, int maior) {
        return meuGerador.nextInt(maior * 10 - menor + 1) + menor;
    }

    public static int obterTamanhoIntersecao(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {

        int repeticoes = 0;
        for (int i = 0; i < lista1.size(); i++) {
            int num1 = lista1.get(i);
            for (int j = 0; j < lista2.size(); j++) {
                int num2 = lista2.get(j);
                if (num1 == num2) {
                    repeticoes++;
                    System.out.println("O número " + lista1.get(i) + " se repete em ambas as listas, nas posições " + (i + 1) + " e " + (j + 1) + ", respectivamente ");
                }
            }
        }
        return repeticoes;
    }

    public static void sortearNumeros(int tamanhoLista1, int tamanhoLista2, ArrayList<Integer> lista1, ArrayList<Integer> lista2) {

        for (int Counter = 0; Counter < tamanhoLista1; Counter++) {
            int numeroSorteado = sortearInt(0, tamanhoLista1);
            lista1.add(numeroSorteado);
        }
        for (int Counter = 0; Counter < tamanhoLista2; Counter++) {
            int numeroSorteado = sortearInt(0, tamanhoLista2);
            lista2.add(numeroSorteado);
        }
        System.out.println("Lista 1: " + lista1);
        System.out.println("Lista 2: " + lista2);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Tamanho da primeira lista: ");
        int Size1 = scan.nextInt();
        System.out.println("Tamanho da segunda lista: ");
        int Size2 = scan.nextInt();
        ArrayList<Integer> lista1 = new ArrayList<>(Size1);
        ArrayList<Integer> lista2 = new ArrayList<>(Size2);
        sortearNumeros(Size1, Size2, lista1, lista2);
        int tamanhoIntesecao = obterTamanhoIntersecao(lista1, lista2);
        if (tamanhoIntesecao != 0) {
            System.out.println("No total, as listas possuem " + tamanhoIntesecao + " interseção(ões) entre si");
        } else {
            System.out.println("As listas não possuem interseções entre si");
        }
    }
}
