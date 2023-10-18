package br.com.fiap.test;

import br.com.fiap.arvores.cnpjABB;
import br.com.fiap.arvores.cpfABB;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*Enzo Pavanello
 * Ilan
 * Leonardo Cardoso
 * Raphael Augusto
 * Vitor Zottino */

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        cnpjABB arvoreCNPJ = new cnpjABB();
        cpfABB arvoreCPF = new cpfABB();
        int opcao, op, numeroConta;
        String nome, cpfCnpj;
        String tipoConta = null;
        double saldo;
        do {
            System.out.println(" 0 - Encerrar o programa");
            System.out.println(" 1 - Inscrição cliente");
            System.out.println(" 2 - Oferta de novo serviço e/ou aplicação");
            System.out.println(" 3 – Entrar no Submenu ");
            opcao = input.nextInt();
            switch (opcao) {
                case 1:
                    Cliente cliente = new Cliente();
                    System.out.print("Digite nome: ");
                    cliente.setNome(input.next());
                    System.out.print("Digite cpf: ");
                    cliente.setCpfCnpj(input.next());
                    Conta conta = new Conta(cliente);
                    System.out.print("Digite número da conta: ");
                    conta.setNumeroConta(input.nextInt());
                    do {
                        System.out.print("Qual o tipo da conta? \n1- Pessoa Física 2- Pessoa Jurídica:");
                        op = input.nextInt();
                        switch (op) {
                            case 1:
                                tipoConta = "Fisica";
                                conta.setTipoConta(tipoConta);
                                break;
                            case 2:
                                tipoConta = "Juridica";
                                conta.setTipoConta(tipoConta);
                                break;
                            default:
                                System.out.println("Opção inválida ");
                                op = -1;
                        }
                    } while (op == -1);
                    System.out.print("Informe saldo em aplicações R$: ");
                    saldo = input.nextDouble();
                    conta.setSaldo(saldo);

                    if (tipoConta.equals("Fisica")) {
                        arvoreCPF.root = arvoreCPF.insert(arvoreCPF.root, conta);
                    } else {
                        arvoreCNPJ.root = arvoreCNPJ.insert(arvoreCNPJ.root, conta);
                    }
                    break;
                case 2:
                    System.out.println("Qual tipo de conta a oferta se destina ? ");
                    do {
                        System.out.println("1- Pessoa Física 2- Pessoa Jurídica:");
                        op = input.nextInt();
                        switch (op) {
                            case 1:
                                tipoConta = "Fisica";
                                break;
                            case 2:
                                tipoConta = "Juridica";
                                break;
                            default:
                                System.out.println("Opção inválida ");
                                op = -1;
                        }
                    } while (op == -1);
                    System.out.print("Qual o valor de saldo mínimo exigido:R$");
                    saldo = input.nextDouble();
                    if (tipoConta.equals("Fisica")) {
                        List<Conta> contas = new ArrayList<>();
                        contas = arvoreCPF.listarContas(arvoreCPF.root, saldo, contas);
                        for (Conta c : contas) {
                            System.out.println(c.getNome() + " Deseja aceitar a oferta ? \n1- Sim 2- Não");
                            if (input.nextInt() == 1) {
                                arvoreCPF.root = arvoreCPF.remover(arvoreCPF.root, c.getNumeroConta());
                                System.out.println(c.getNome() + " removido da arvore");
                            } else {
                                System.out.println("Proximo cliente");
                            }

                        }
                        System.out.println("Fim da lista de clientes validos para a oferta");
                    } else {
                        List<Conta> contas = new ArrayList<>();
                        contas = arvoreCNPJ.listarContas(arvoreCNPJ.root, saldo, contas);
                        for (Conta c : contas) {
                            System.out.println(c.getNome() + " Deseja aceitar a oferta ? \n1- Sim 2- Não");
                            if (input.nextInt() == 1) {
                                arvoreCNPJ.root = arvoreCNPJ.remover(arvoreCNPJ.root, c.getNumeroConta());
                                System.out.println(c.getNome() + " removido da arvore");
                            } else {
                                System.out.println("Proximo cliente");
                            }

                        }
                        System.out.println("Fim da lista de clientes validos para a oferta");


                    }
                    break;
                case 3:
                    System.out.println("1- Consultar Cliente");
                    System.out.println("2- Atualizar saldo");
                    System.out.println("3- Total de clientes");
                    System.out.println("4- Total de clientes com saldo");
                    System.out.println("0- Voltar ao menu principal");
                    switch (input.nextInt()) {
                        case 1:
                            System.out.println("Qual o tipo da conta? \n1- CPF 2-CNPJ");
                            if (input.nextInt() == 1) {
                                System.out.println("Informe o CPF a ser consultado");
                                Conta c = arvoreCPF.consultar(arvoreCPF.root, input.next());
                                if (c == null) {
                                    System.out.println("CPF nao foi encontrado em nossa base de dados");
                                } else {
                                    System.out.println(c.toString());
                                }

                            } else {
                                System.out.println("Informe o CNPJ a ser consultado");
                                Conta c = arvoreCNPJ.consultar(arvoreCNPJ.root, input.next());
                                if (c == null) {
                                    System.out.println("CNPJ nao foi encontrado em nossa base de dados");
                                } else {
                                    System.out.println(c.toString());
                                }

                            }
                            break;
                        case 2:
                            System.out.println("Qual o tipo da conta? \n1- CPF 2- CNPJ");
                            if (input.nextInt() == 1) {
                                System.out.println("Informe o numero da conta a ser atualizada");
                                int num = input.nextInt();
                                System.out.println("Informe o novo saldo");
                                double sal = input.nextDouble();
                                arvoreCPF.atualizarSaldo(arvoreCPF.root, num, sal);
                            } else {
                                System.out.println("Informe o numero da conta a ser atualizada");
                                int num = input.nextInt();
                                System.out.println("Informe o novo saldo");
                                double sal = input.nextDouble();
                                arvoreCNPJ.atualizarSaldo(arvoreCNPJ.root, num, sal);
                            }
                            break;

                        case 3:
                            System.out.println("Qual o tipo da conta? \n1- CPF 2- CNPJ");
                            if (input.nextInt() == 1) {
                                System.out.println("Total de contas CPF cadastradas: " + arvoreCPF.contaNos(arvoreCPF.root, 0));
                            } else {
                                System.out.println("Total de contas CNPJ cadastradas: " + arvoreCNPJ.contaNos(arvoreCNPJ.root, 0));
                            }
                            break;

                        case 4:
                            System.out.println("Informe o saldo");
                            double sal = input.nextInt();
                            System.out.println("Qual o tipo da conta? \n1- CPF 2- CNPJ");
                            if (input.nextInt() == 1) {
                                System.out.println("Total de contas CPF cadastradas: " + arvoreCPF.contasSaldo(arvoreCPF.root, 0, sal));
                            } else {
                                System.out.println("Total de contas CNPJ cadastradas: " + arvoreCNPJ.contasSaldo(arvoreCNPJ.root, 0, sal));
                            }
                            break;

                        case 0:
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Lista CPF");
                    List<Conta> teste = new ArrayList<>();
                    teste = arvoreCPF.listarContas(arvoreCPF.root, 0, teste);
                    for (Conta c : teste) {
                        System.out.println(c);
                    }
                    System.out.println("Lista CNPJ");
                    arvoreCNPJ.listarEmOrdem(arvoreCNPJ.root);
                    break;
            }
        } while (opcao != 0);

        System.out.println("Clientes que não aceitaram ou não estavam adequados para a oferta");
        System.out.println("Lista CPF");
        arvoreCPF.listarEmOrdem(arvoreCPF.root);
        System.out.println("Lista CNPJ");
        arvoreCNPJ.listarEmOrdem(arvoreCNPJ.root);
        input.close();
    }
}


