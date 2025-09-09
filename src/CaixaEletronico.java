import java.sql.ClientInfoStatus;
import java.sql.SQLOutput;
import java.util.InputMismatchException; //CClasse para verificação de erros de entrada
import java.util.Scanner;

public class CaixaEletronico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //DADOS DO BANCO
        //Agora criamos instâncias das classes específicas
        Cliente cliente1 = new Cliente("Carlinhos Maia", "726.038.922-18");
        Conta conta1 = new ContaCorrente(cliente1); //Carlinhos tem uma conta corrente
        conta1.depositar(1500.0);

        Cliente cliente2 = new Cliente("Fabio Maneiro", "372.047.822-10");
        Conta conta2 = new ContaPoupanca(cliente2); //Fabio tem uma conta poupança
        conta2.depositar(100000.0);

        //Colocamos as contas em um array do tipo de superclasse(conta)
        //Isso é Polimorfismo: o array pode guardar qualquer objeto que SEJA UMA conta
        Conta[] contasDoBanco = {conta1, conta2};
        Conta contaAtiva = contasDoBanco[0];

        int opcao = 0;
        while (opcao != 0) {
            System.out.println("\n--- CAIXA ELETRÔNICO ZZ BANK ---");
            System.out.println("Bem-vindo(a), " + contaAtiva.getNomeTitular() + " | Conta: " + contaAtiva.getClass().getSimpleName());
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Tranferir");
            System.out.println("5 - Trocar de conta (Login)");

            // --- OPÇÕES ESPECÍFICAS DE CADA CONTA ---
            //O operador 'instanceof' verifica o tipo real do objeto
            if (contaAtiva instanceof ContaCorrente) {
                System.out.println("6 - Cobrar Taxa de Manutenção");
            }
            if (contaAtiva instanceof ContaPoupanca) {
                System.out.println("7 - Fazer render Juros");
            }

            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");

            try { //verificação de erros com o catch
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        contaAtiva.exibirDados();
                        break;
                    case 2:
                        System.out.print("Digite o valor para depositar: ");
                        contaAtiva.depositar(scanner.nextDouble());
                        break;
                    case 3:
                        System.out.print("Digite o valor para sacar: ");
                        contaAtiva.sacar(scanner.nextDouble());
                        break;
                    case 4:
                        System.out.print("Digite o número da conta destino: ");
                        int numContaDestino = scanner.nextInt();
                        Conta contaDestino = null;
                        for (Conta c : contasDoBanco) if (c.getNumero() == numContaDestino) contaDestino = c;

                        if (contaDestino != null && contaDestino != contaAtiva) {
                            System.out.print("Digite o valor para transferir: ");
                            contaAtiva.transferir(contaDestino, scanner.nextDouble());

                        } else {
                            System.out.println("Conta destino inválida ou igual à origem.");
                        }
                        break;
                    case 5:
                        System.out.println("\n--- CONTAS DISPONÍVEIS ---");
                        for (Conta c : contasDoBanco) {
                            System.out.println("N° " + c.getNumero() + " - " + c.getClass().getSimpleName() + " - " + c.getNomeTitular());
                        }
                        System.out.print("Digite o número da conta desejada: ");
                        int numContaLogin = scanner.nextInt();
                        boolean encontrada = false;
                        for (Conta c : contasDoBanco) {
                            if (c.getNumero() == numContaLogin) {
                                contaAtiva = c;
                                encontrada = true;
                                break;
                            }
                        }
                        if (encontrada) System.out.println("Login efetuado com sucesso!");
                        else System.out.println("Conta não encontrada.");
                        break;
                    case 6:
                        if (contaAtiva instanceof ContaCorrente) {
                            //É preciso fazer um "cast" para acessar o metodo especifico
                            //Dizemos ao compilador: "Trate este objeto como uma ContaCorrente".

                        } else {
                            System.out.println("Opção válida apenas para Contas Correntes.");
                        }
                        break;
                    case 7:
                        if (contaAtiva instanceof ContaPoupanca) {
                            //Fazendo o "cast" para ContaPoupanca
                            ((ContaPoupanca) contaAtiva).renderJuros();
                        } else {
                            System.out.println("Opção válida apenas apra Contas Poupança.");
                        }
                        break;
                    case 8:
                        System.out.println("\nObrigado por usar o ZZ Bank. Volte sempre!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas números .");
                scanner.next();
            }
        }
        scanner.close();
    }
}