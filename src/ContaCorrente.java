//ContaCorrente É UMA Conta. Herda tudo de Conta.
public class ContaCorrente extends Conta{
    private double taxaDeManutencao = 15.90;

    public ContaCorrente(Cliente titular) {
        //CHama o construtor da classe mãe (conta).
        super(titular);
    }

    //Metodo que SÓ a ContaCorrente tem.
    public void cobrarTaxa() {
        this.saldo -= this.taxaDeManutencao;
        System.out.println("Taxa de manutenção de R$ " + taxaDeManutencao + " cobrada da conta " + this.numero);
    }
}
