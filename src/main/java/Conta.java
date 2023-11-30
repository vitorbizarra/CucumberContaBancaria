import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

/**
 * A classe Conta representa uma conta
 * bancária com saldo e operações de saque.
 */
public class Conta {

    /**
     * Saldo da conta
     */
    private int saldo;

    /**
     * Verifica se saldo é suficiente para saque
     */
    private boolean saqueSuficiente;

    /**
     * Inicializa um cliente especial com o saldo informado
     * 
     * @param arg1 - Valor atual do saldo do cliente
     */
    @Given("^Um cliente especial com saldo atual de -(\\d+) reais$")
    public void um_cliente_especial_com_saldo_atual_de_reais(int arg1) {
        criarClienteEspecialComSaldoNegativo(arg1);
    }

    /**
     * Inicializa um cliente comum com o saldo informado
     * 
     * @param arg1 - Valor atual do saldo do cliente
     */
    @Given("^Um cliente comum com saldo atual de -(\\d+) reais$")
    public void um_cliente_comum_com_saldo_atual_de_reais(int arg1) {
        criarClienteComumComSaldoNegativo(arg1);
    }

    /**
     * Realiza um saque na conta, se o saldo for suficiente.
     *
     * @param arg1 Valor do saque a ser realizado.
     */
    @When("^for solicitado um saque no valor de (\\d+) reais$")
    public void for_solicitado_um_saque_no_valor_de_reais(Integer arg1) {

        if (arg1 < 0 || this.saldo + arg1 < 0) {
            this.saqueSuficiente = false;
            return;
        }

        this.saqueSuficiente = true;
    }

    /**
     * Verifica se o saque foi efetuado com sucesso e atualiza o saldo da conta.
     *
     * @param arg1 Novo valor do saldo da conta após o saque (negativo).
     */
    @Then("^deve efetuar o saque e atualizar o saldo da conta para -(\\d+) reais$")
    public void deve_efetuar_o_saque_e_atualizar_o_saldo_da_conta_para_reais(Integer arg1) {

        if (saldo != -arg1) {
            System.out.println("Erro ao efetuar o saque.");
            return;
        }

        System.out.println("Saque realizado com sucesso. Novo saldo: " + saldo);
    }

    /**
     * Verifica se o saque não foi efetuado devido a saldo insuficiente.
     *
     */
    @Then("Não deve efetuar o saque e deve retornar a mensagem Saldo Insuficiente.")
    public void nao_deve_efetuar_o_saque_e_deve_retornar_a_mensagem_Saldo_Insuficiente() {
        
        if (!saqueSuficiente) {
            System.out.println("Saque não realizado. Saldo insuficiente.");
            return;
        }

        System.out.println("Erro ao verificar o saldo insuficiente.");
    }

    /**
     * Cria um cliente especial com saldo inicial negativo.
     *
     * @param arg1 Valor inicial do saldo do cliente especial (negativo).
     */
    private void criarClienteEspecialComSaldoNegativo(Integer arg1) {
        saldo = -arg1;
    }

    /**
     * Cria um cliente comum com saldo inicial negativo.
     *
     * @param arg1 Valor inicial do saldo do cliente comum (negativo).
     */
    private void criarClienteComumComSaldoNegativo(Integer arg1) {
        saldo = -arg1;
    }
}