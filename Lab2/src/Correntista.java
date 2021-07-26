import java.lang.reflect.Constructor;
import java.util.Scanner;

public class Correntista {

    private final long cpf;
    private String nome;

    public Correntista(String nome, long cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome(){
        return nome;
    }
}