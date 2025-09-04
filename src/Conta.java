public class Conta {
    //Protected permite que as classes filhas acessem diretamente estes atributos.
    protected int numero;
    protected Cliente titular;
    protected double saldo;
    private static int totalDeContas = 0;

    //Construtor que será chamado pelas classes filhas via 'super()'
    public Conta(Cliente titular) {
        Conta.totalDeContas++;
        this.numero = totalDeContas;
        this.titular = titular;
        this.saldo = 0;
    }

    //Metodos concretos que serão herdados por todas as contas filhas.
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido");
        }
    }

public boolean sacar(double valor) {
    if (valor > 0 && this.saldo>= valor) {
        this.saldo -= valor;
        System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
        return true;
    }else {
        System.out.println("Operação falhou! Saldo insuficiente ou valor inválido.");
        return false;
    }
}

public void transferir (Conta destino, double valor) {
    if(this.sacar(valor)) {
        destino.depositar(valor);
        System.out.println("Tranferência concluída.");
    }else {
        System.out.println("Tranferência não realizada.");
    }
}

public double getSaldo() { return this.saldo; }
public int getNumero() { return this.numero; }
public String getNomeTitular() { return this.titular.getNome(); }

public void exibirDados() {
    System.out.println("--- Dados da Conta");
    //getClass().getSimpleName() mostra o nome da classe específica (ContaCprrente ou ContaPoupanca)
    System.out.println("Tipo....: " + this.getClass().getSimpleName());
    System.out.println("Titular.: " + this.getNomeTitular());
    System.out.println("Número..: " + this.getNumero());
    System.out.println("Saldo...: R$ " + String.format("%.2f", this.getSaldo()));
    System.out.println("-------------------------");
    }
}