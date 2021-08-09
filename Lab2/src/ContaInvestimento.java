public class ContaInvestimento extends Conta {
    private String tipoInvestimento;
    private float taxaJuros, saldoTemporario;
    // A taxa de juros deve ser dada em porcentagem, mas sem o símbolo %
    // Por exemplo, para uma taxa de 5%, deve-se dar input "5"
    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoInvestimento, float taxaJuros) {
        super(numeroDaConta, correntista);
        if (correntista.getContaCorrente() == null){
            throw new RuntimeException("Correntista sem conta corrente!");
        }
        tipoInvestimento = this.tipoInvestimento;
        taxaJuros = this.taxaJuros;
    }
    //Atualizar o saldo de acordo com a taxa de juros
    public float aplicarJuros(float taxaJuros, String tipoInvestimento){
        saldoTemporario = ((saldoTemporario + this.getSaldoEmReais())* (1 + (taxaJuros/100)) - this.getSaldoEmReais());
        return saldoTemporario;
    }
    //Resgatar o valor para a conta corrente
    public void resgatarTotal(){
        this.receberDepositoEmDinheiro(saldoTemporario);
    }
}
