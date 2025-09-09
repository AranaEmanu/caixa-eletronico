//ContaPoupanca É UMA Conta.
public class ContaPoupanca extends Conta {
    private double taxaDeJuros = 0.005; //o.5% ao mês

    public ContaPoupanca(Cliente titular) {
        //Chama o Construtor da calsse mãe (Conta).
        super(titular);
    }

    //Metodo que SÓ a ContaPoupanca tem.
    public void renderJuros() {
        double juros = this.saldo * this.taxaDeJuros;
        this.saldo += juros;
        System.out.println("Juros de R$ " + String.format("%.2f", juros) + " renderam na conta " + this.numero);
        this.saldo += juros;
        System.out.println("Juros de R$" + String.format("%.2f",juros)+ "rederam na conta" + this.numero);
    }
}
