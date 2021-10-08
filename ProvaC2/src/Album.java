import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta classe implementa um álbum (de figurinhas, selos, etc.) online, permitindo
 * colecionar itens que possuem uma posição específica no álbum.
 * <p>
 * Itens são acrescentados ao álbum por meio de "pacotinhos" contendo uma quantidade
 * fixa, pré-feterminada de itens.
 * <p>
 * Itens já existentes no álbum e recebidos novamente em pacotinhos posteriores são
 * armazenados para eventuais trocas com outro usuários.
 *
 * @param <T> o tipo de objeto colecionável que o álbum irá armazenar
 */
public class Album<T extends Colecionavel> {
    private Map<Integer, Boolean> mapaDeFigurinhas = new HashMap<Integer, Boolean>();
    private ArrayList<T> listaDeColadas = new ArrayList<>();
    private ArrayList<T> listaDeRepetidas = new ArrayList<>();
    public static int quantItensPorPacotinho;
    private int quantItensRepetidos = 0;

    /**
     * Construtor.
     *
     * @param tamanhoDoAlbum         O tamanho do álbum sendo criado (note que os itens que serão colecionados
     *                               terão posições entre 1 e o tamanho do álbum)
     * @param quantItensPorPacotinho A quantidade de itens em cada pacotinho adquirido para este álbum
     */
    public Album(int tamanhoDoAlbum, int quantItensPorPacotinho) {
        for (int i = 1; i <= tamanhoDoAlbum; i++) {
            mapaDeFigurinhas.put(i, false);
            this.quantItensPorPacotinho = quantItensPorPacotinho;
        }
    }

    /**
     * Recebe novos itens que serão imediatamente "colados" ao álbum, se inéditos,
     * ou guardados para troca, se repetidos.
     *
     * @param pacotinho Um pacotinho de itens, que poderão ser inéditos ou repetidos
     * @throws PacotinhoInvalidoException se o pacotinho contiver uma quantidade errada de itens,
     *                                    ou se contiver algum item que não pertença ao álbum
     *                                    (por indicar uma posição menor que 1 ou maior que seu tamanho)
     */
    public void receberNovoPacotinho(Pacotinho<T> pacotinho) throws PacotinhoInvalidoException {
        if (pacotinho.size() < quantItensPorPacotinho){
            throw new PacotinhoInvalidoException();
        }
        for (T t: pacotinho){
            if (t.getPosicao() > mapaDeFigurinhas.size() || t.getPosicao() <= 0){
                throw new PacotinhoInvalidoException();
            }
        }
        for (T t: pacotinho){
            if (t.getPosicao() > mapaDeFigurinhas.size()){
                //Checando figurinhas inválidas
                throw new PacotinhoInvalidoException();
            }
            if (mapaDeFigurinhas.get(t.getPosicao())){
                //Checando itens repetidos
                listaDeRepetidas.add(t);
                quantItensRepetidos++;
            }
            else {
                mapaDeFigurinhas.put(t.getPosicao(),true);
                listaDeColadas.add(t);
            }
        }
    }

    /**
     * @return a quantidade total de figurinhas que este álbum apresenta quando se encontra completo
     */
    public int getTamanho() {
        return mapaDeFigurinhas.size();
    }

    /**
     * @return a quantidade total de itens que estão "colados" no álbum,
     * isto é, o total de itens distintos que foram recebidos até o momento
     */
    public int getQuantItensColados() {
        int contadorDeColadas = 0;
        for (int i = 1; i <= mapaDeFigurinhas.size(); i++) {
            if (mapaDeFigurinhas.get(i)) {
                contadorDeColadas++;
            }
        }
        return contadorDeColadas;
    }

    /**
     * @return o total de itens repetidos que foram acumulados até o momento
     */
    public int getQuantItensRepetidos() {
        return quantItensRepetidos;
    }

    public boolean possuiItemColado(int posicao) {
        if (posicao > mapaDeFigurinhas.size() || posicao <= 0){
            return false;
        }
        return mapaDeFigurinhas.get(posicao);
    }

    public boolean possuiItemRepetido(int posicao) {
        for (T t: listaDeRepetidas){
            if (t.getPosicao() == posicao){
                return true;
            }
        }
        return false;
    }

    /**
     * @return a quantidade de itens que faltam para o álbum ficar completo
     */
    public int getQuantItensFaltantes() {
        int quantItensFaltantes = 0;
        for (int i = 1; i <= mapaDeFigurinhas.size(); i++){
            if (!mapaDeFigurinhas.get(i)){
                quantItensFaltantes++;
            }
        }
        return quantItensFaltantes;
    }

    /**
     * @param posicao a posição do iten desejado
     * @return o item que está colado na posição especificada, se houver; null, caso contrário
     */
    public T getItemColado(int posicao) {
        for (T t: listaDeColadas){
            if (t.getPosicao() == posicao){
                return t;
            }
        }
        return null;
    }
}
