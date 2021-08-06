public class ContaInvestimento extends Conta {
    private String tipoInvestimento;
    private float taxaJuros, saldoContaInvestimento = 0;
    Correntista correntista;
    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoInvestimento, float taxaJuros) {
        super(numeroDaConta, correntista);
        if (correntista.getContaCorrente() == null){
            throw new RuntimeException("Correntista sem conta corrente!");
        }
        correntista = this.correntista;
        tipoInvestimento = this.tipoInvestimento;
        taxaJuros = this.taxaJuros;
    }

    //Atualizar o saldo de acordo com o valor recebido
    public void aplicarNaConta(float valorInvestido){
        saldoContaInvestimento += ContaInvestimento.super.aplicarDinheiro(valorInvestido);
    }
    //Atualizar o saldo de acordo com a taxa de juros
    public float aplicarJuros(float taxaJuros, String tipoInvestimento, float valorInvestido){
        ContaInvestimento.super.efetuarTransferecia(this, valorInvestido);
        saldoContaInvestimento = saldoContaInvestimento * (1+ taxaJuros/100);
        return saldoContaInvestimento;
    }
    //Resgatar o valor para a conta corrente
    public void resgatarTotal(){
        this.efetuarTransferecia(this.correntista.getContaCorrente(), saldoContaInvestimento);
        saldoContaInvestimento = 0;
    }
}
