package controle;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import modelo.Livro;
import modelo.Usuario;

import java.util.*;

public class Biblioteca {
    Map<Long, Usuario> mapaDeUsuariosPorCpf = new HashMap<>();
    Map<Livro, Integer> mapaDeLivros = new HashMap<>();
    int quantidadeDeLivros;
    long usuariosCadastrados = 0;
    /** quantidade mínima de unidades de um livro que precisam existir nas estantes da biblioteca para
     que o livro possa ser emprestado */
    public static final int MIN_COPIAS_PARA_PODER_EMPRESTAR = 2;

    /** quantidade máxima de livros da biblioteca que podem estar emprestados, a qualquer tempo, a um mesmo usuário */
    public static final int MAX_LIVROS_DEVIDOS = 3;

    public Biblioteca() {
        quantidadeDeLivros = 0;
    }

    /**
     * Cadastra um usuário. Caso o usuário já exista, atualiza seu nome e seu endereço.
     *
     * @param usuario o usuário a ser inserido/atualizado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        if(mapaDeUsuariosPorCpf.containsKey(usuario.getCpf())){
            mapaDeUsuariosPorCpf.get(usuario.getCpf()).setNome(usuario.getNome());
            mapaDeUsuariosPorCpf.get(usuario.getCpf()).setEndereco(usuario.getEndereco());
        }
        else {
            mapaDeUsuariosPorCpf.put(usuario.getCpf(),usuario);
            usuariosCadastrados++;
        }
    }

    /**
     * Retorna um usuário previamente cadastrado, a partir do CPF informado.
     *
     * @param cpf o CPF do usuário desejado
     * @return o usuário, caso exista; ou null, caso não exista nenhum usuário cadastrado com aquele CPF
     */
    public Usuario getUsuario(long cpf) {
        return mapaDeUsuariosPorCpf.get(cpf);
    }

    /**
     * @return o número total de usuários cadastrados na biblioteca
     */
    public long getTotalDeUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    /**
     * Adquire `quantidade` cópias do livro informado e as inclui no acervo da biblioteca.
     *
     * @param livro o livro sendo adquirido
     * @param quantidade o número de cópias do livro sendo adquiridas
     */
    public void incluirLivroNoAcervo(Livro livro, int quantidade) {
        if (mapaDeLivros.containsKey(livro)){
            int quantidadeTemporaria = mapaDeLivros.get(livro);
            mapaDeLivros.put(livro, quantidadeTemporaria + quantidade);
        }
        else{
            mapaDeLivros.put(livro,quantidade);
        }
        quantidadeDeLivros += quantidade;
    }

    /**
     * Empresta um livro para um usuário da biblioteca.
     *
     * @param livro o livro a ser emprestado
     * @param usuario o usuário que está pegando emprestado o livro
     *
     * @return true, se o empréstimo foi bem-sucedido;
     *         false, se o livro não está disponível para empréstimo
     *         (IMPORTANTE: um livro é considerado disponível para empréstimo se há pelo menos
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR cópias daquele livro nas estantes da biblioteca;
     *                      isto é, as estantes da biblioteca jamais poderão ficar com menos do que
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR-1 cópias de qualquer livro de seu acervo)
     *
     * @throws UsuarioNaoCadastradoException se o usuário informado não está cadastrado na biblioteca
     * @throws LimiteEmprestimosExcedidoException se o usuário já está com muitos livros emprestados no momento
     */
    public boolean emprestarLivro(Livro livro, Usuario usuario)
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {
        if (!mapaDeUsuariosPorCpf.containsKey(usuario.getCpf())){
            throw new UsuarioNaoCadastradoException();
        }
        else if (!mapaDeLivros.containsKey(livro)){
            return false;
        }
        else if(usuario.getLivrosDevidos() >= MAX_LIVROS_DEVIDOS){
            throw new LimiteEmprestimosExcedidoException();
        }
        else if(mapaDeLivros.get(livro) >= MIN_COPIAS_PARA_PODER_EMPRESTAR){
            //Quando, de fato emprestar:
            usuario.pegarLivro(livro);
            int quantidadeTemporaria = mapaDeLivros.get(livro);
            mapaDeLivros.put(livro, quantidadeTemporaria-1);
            return true;
        }
        return false;
    }

    /**
     * Recebe de volta um livro que havia sido emprestado.
     *
     * @param livro o livro sendo devolvido
     * @param usuario o usuário que havia tomado emprestado aquele livro
     *
     * @throws DevolucaoInvalidaException se o livro informado não se encontra emprestado para o usuário informado
     */
    public void receberDevolucaoLivro(Livro livro, Usuario usuario) throws DevolucaoInvalidaException {
        if(usuario.contemLivro(livro)){
            usuario.devolverLivro(livro);
            int quantidadeTemporaria = mapaDeLivros.get(livro);
            mapaDeLivros.put(livro, quantidadeTemporaria+1);
        }
        else{
            throw new DevolucaoInvalidaException();
        }
    }

    /**
     * Retorna a quantidade de livros emprestados ao usuário informado.
     *
     * @param usuario o usuário desejado
     * @return a quantidade de livros emprestados àquele usuário; caso o usuário não esteja devendo nenhum livro,
     *         ou não seja nem mesmo um usuário cadastrado na biblioteca, retorna zero, não deve nada
     */
    public int getQuantidadeDeLivrosDevidos(Usuario usuario) {
        if (!mapaDeUsuariosPorCpf.containsKey(usuario.getCpf())){
            return 0;
        }
        return usuario.getLivrosDevidos();
    }

    /**
     * @return a quantidade total de livros nas estantes da biblioteca (isto é, a soma das quantidades de cópias
     *         não-emprestadas de todos os livros do acervo da biblioteca).
     */
    public int getQuantidadeDeLivrosNaEstante() {
        return quantidadeDeLivros;
    }

    /**
     * Retorna o número de cópias do livro informado que existem na estante da biblioteca
     * (isto é, que não estão emprestados).
     *
     * @param livro o livro desejado
     * @return o número de cópias não-emprestadas daquele livro
     */
    public int getQuantidadeDeLivrosNaEstante(Livro livro) {
        return mapaDeLivros.getOrDefault(livro, 0);

    }
    /*2) Eu criaria os atributos int "categoria" e int "score" dentro de "Usuario", desta forma, sempre que o Usuario
pegasse um Livro emprestado, o código, primeiramente, verificaria a categoria que ele se encontra (observando,
através de um switch,quantos livros esse usuario poderia pegar emprestado), em seguida o código aumentaria o score
e, finalmente, verificaria se esse usuario merece uma promoção.

3) Criaria uma Superclasse "Item" com Subclasses Livro, Jogo, Revista e DVD. A regra de negócio relacionada ao
limite de empréstimos se referiria à qualquer Item, não só mais à livros.

4) Criaria uma Superclasse "Biblioteca" com subclasses referentes à cada tipo referenciado, que dão overwrite nas
funções emprestar() e receberDevolucao()

*/
}