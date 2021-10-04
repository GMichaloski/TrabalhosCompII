package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
    String nome, endereco;
    private ArrayList<Livro> livrosDoUsuario = new ArrayList<>();
    private final long cpf;
    public Usuario(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public void pegarLivro(Livro livro){
        livrosDoUsuario.add(livro);
    }
    public void devolverLivro(Livro livro){
        livrosDoUsuario.remove(livro);
    }
    public int getLivrosDevidos(){
        return livrosDoUsuario.size();
    }
    public boolean contemLivro(Livro livro){
        return livrosDoUsuario.contains(livro);
    }
    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return cpf == usuario.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
