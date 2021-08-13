import java.util.ArrayList;

public class Documento {
    private ArrayList<String> paginas;
    private boolean emCores;

    public Documento(ArrayList<String> paginas, boolean emCores) {
        this.paginas = paginas;
        this.emCores = emCores;
    }

    public boolean isEmCores() {
        return this.emCores;
    }

    public int getQuantPaginas() {
        return paginas.size();
    }

    public String getPagina(int numeroDaPagina) {
        // Se diminui 1 unidade para igualar a sintaxe humana do index com a de computação
        return paginas.get(numeroDaPagina - 1);
    }
}