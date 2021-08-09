import java.util.ArrayList;
import java.util.Date;


public class Conta {

    private final int numero;

    private Correntista correntista;

    private float saldoEmReais = 0;

    private Gerente gerente;

    private ArrayList<String> transacoes;

    private boolean ativa;

    public static final float SALDO_INICIAL_DA_CONTA = 10;  // "constante"

    private static int quantidadeDeTransacoesDeTodasAsContas = 0;


    // CONSTRUTOR: método especial que roda quando chamamos o "new" para instanciar
    public Conta(int numeroDaConta, Correntista correntista) {
        this.correntista = correntista;
        this.correntista.setContaCorrente(Conta.this);
        this.numero = numeroDaConta;
        this.saldoEmReais = SALDO_INICIAL_DA_CONTA;  // saldo inicial doado pelo banco
        this.transacoes = new ArrayList<>();
        this.transacoes.add("Conta criada com saldo de " + this.saldoEmReais);
        this.ativa = true;
    }

    public float getSaldoEmReais() {
        return this.saldoEmReais;
    }

    public void receberDepositoEmDinheiro(float valor) {
        if (valor <= 0) {
            return;  // valor inválido; não faz nada!
        } else {
            this.saldoEmReais += valor;
        }

//        Date agora = new Date();  // now

        String registroTransacao = "recebido depósito em dinheiro: " + valor;

        this.transacoes.add(registroTransacao);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    public String getNome() {
        String nome = this.correntista.getNome();
        return nome;
    }

    public long getCpfDoCorrentista() {
        long cpfDoCorrentista = this.correntista.getCpf();
        return cpfDoCorrentista;
    }

    public String getExtrato() {
        String resultado = "";
        for (int i = 0; i < transacoes.size(); i++) {
            resultado += transacoes.get(i) + "\n";
        }
        return resultado;
    }

    /**
     * Retorna a quantidade total de transações do banco, ou seja,
     * de todas as contas correntes que já foram criadas.
     *
     * @return o total de transações
     */
    public static int getQuantidadeDeTransacoesDeTodasAsContas() {
        return quantidadeDeTransacoesDeTodasAsContas;
    }

    public void sacar(float valor) {
        if ((valor > 0) && (valor <= this.saldoEmReais)) {
            this.saldoEmReais -= valor;
            quantidadeDeTransacoesDeTodasAsContas++;
        }
    }

    public void efetuarTransferecia(Conta contaDestino, float valor) {
        if ((valor > 0) && (valor <= this.saldoEmReais)) {
            this.saldoEmReais -= valor;
            contaDestino.receberDepositoEmDinheiro(valor);
        }
    }

    private void registrarTransacao(String registro) {
        String dataAtual = obterDataAtualAsString();
        this.transacoes.add(dataAtual + ": " + registro);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    String obterDataAtualAsString() {
        return String.format("%s", new Date());
    }

    public void encerrar() {
        if (this.saldoEmReais < 0) {
            // ToDo tirar dúvida exceção
            // não deixa encerrar conta com saldo negativo
        }
        this.ativa = false;  // desativou a conta

        System.out.printf("\nConta %d encerrada", this.numero);
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente novaGerente) {
        if (this.gerente != null) {
            // avisa ao gerente antigo que ele não é mais gerente
            this.gerente.deixarDeGerenciarConta(this);
        }
        this.gerente = novaGerente;
    }

}