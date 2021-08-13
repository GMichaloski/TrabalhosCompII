public abstract class Impressora {

    private int folhasParaImprimir, documentosImpressos;

    public boolean imprimirDocumento(Documento documento) {
        // verifica se há papel suficiente (se não houver, não imprime)
        if (folhasParaImprimir < documento.getQuantPaginas()) {
            return false;
        } else {
            // incrementa o número de documentos impressos
            documentosImpressos++;
            // para cada página, manda imprimir de fato
            for (int i = 1; i <= documento.getQuantPaginas(); i++) {
                executarImpressaoPagina(documento.getPagina(i));
                folhasParaImprimir--;
            }
            return true;
        }
    }

    public void carregarPapel(int numeroDeFolhas) {
        folhasParaImprimir = folhasParaImprimir + numeroDeFolhas;
    }

    public int getQuantidadeFolhasRestantes() {
        return folhasParaImprimir;
    }

    public int getQuantidadeDocumentosImpressos() {
        return documentosImpressos;
    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}