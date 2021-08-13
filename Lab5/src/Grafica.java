import java.util.ArrayList;

public class Grafica {
    private int revezador = 0;
    private float precoColorida, precoPB;
    private ArrayList<Impressora> Impressoras;

    public Grafica() {
        Impressoras = new ArrayList<>();
    }

    public void imprimirDocumento(Documento documento) {
        if (revezador >= Impressoras.size()) {
            revezador = 0;
        }
        for (int i = 0; i < Impressoras.size(); i++) {
            if (revezador == i) {
                Impressoras.get(i).imprimirDocumento(documento);
                revezador++;
                break;
            }
        }
    }

    public float orcarImpressao(Documento documento) {
        if (documento.isEmCores()) {
            return precoColorida * documento.getQuantPaginas();
        } else {
            return precoPB * documento.getQuantPaginas();
        }
    }

    public void adicionarImpressora(Impressora impressora) {
        Impressoras.add(impressora);
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {
        if (emCores) {
            precoColorida = precoPorPagina;
        } else {
            precoPB = precoPorPagina;
        }
    }
}