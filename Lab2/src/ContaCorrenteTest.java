import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    // para cobrir pequenos erros de precisão do tipo float
    private final float FLOAT_DELTA = 0.00001f;

    private Conta contaDoJoao;
    private Correntista joao;
    private float saldoInicial;

    private Conta contaDaMaria;
    private Correntista maria;


    @Before
    public void setUp() {
        joao = new Correntista( "Joao", 40028922);
        contaDoJoao = new Conta(1, joao);
        saldoInicial = contaDoJoao.getSaldoEmReais();

        maria = new Correntista("Maria", 22222);
        contaDaMaria = new Conta(2, maria);
    }

    @Test
    public void testarSaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.",
                10.0,
                saldoInicial,
                FLOAT_DELTA);
    }

    @Test
    public void testarGetCpf(){
        long testeCpf = contaDaMaria.getCpfDoCorrentista();
        assertEquals("Toda conta, ao ser criada, deve conter um cpf atribuído à ela",testeCpf,maria.getCpf(),
                FLOAT_DELTA);

    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito",
                saldoInicial + 50,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem sequer constar do extrato",
                extratoAntes, extratoDepois);

    }

    @Test
    public void testarSaqueComFundos() {
        contaDoJoao.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta",
                saldoInicial - 2,
                contaDoJoao.getSaldoEmReais(),FLOAT_DELTA
        );
    }

    @Test
    public void testarSaqueSemFundos() {
        contaDoJoao.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),FLOAT_DELTA
        );
    }
    
    @Test
    public void testGetQuantidadeDeTransacoesDeTodasAsContas(){
        int temp = Conta.getQuantidadeDeTransacoesDeTodasAsContas();
        contaDoJoao.efetuarTransferecia(contaDaMaria, 5);
        contaDaMaria.sacar(11);

        assertEquals("Todas as operações bancárias devem ser registradas.", temp + 2,
                Conta.getQuantidadeDeTransacoesDeTodasAsContas(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferencia() {

        contaDoJoao.efetuarTransferecia(contaDaMaria, 3);

        assertEquals("O valor recebido deve ser adicionado ao saldo da conta",
                saldoInicial + 3,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals("O valor transferido deve ser descontado do saldo da conta",
                saldoInicial - 3,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarTransferenciaSemFundos() {

        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000);

        assertEquals("Não se pode receber valor de uma transferência inválida",
                saldoInicial,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals("Transferência de valores maiores do que o saldo não são permitidas",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarContaInvestimento(){
        String tipoDeInvestimento = "Tesouro Direto";
        float taxaDeJuros = 5;
        ContaInvestimento contaDoJoao = new ContaInvestimento(1,joao,tipoDeInvestimento,taxaDeJuros);
        contaDoJoao.sacar(contaDoJoao.getSaldoEmReais());
        contaDoJoao.receberDepositoEmDinheiro(100);

        contaDoJoao.aplicarJuros(taxaDeJuros,tipoDeInvestimento);
        assertEquals("O valor do saldo não é alterado até ser resgatado",
                100,contaDoJoao.getSaldoEmReais(),FLOAT_DELTA);

        contaDoJoao.resgatarTotal();
        assertEquals("",105,contaDoJoao.getSaldoEmReais(),FLOAT_DELTA);

    }

}