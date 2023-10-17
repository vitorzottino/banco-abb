package br.com.fiap.arvores;

import br.com.fiap.model.Conta;

import java.util.ArrayList;
import java.util.List;

public class cnpjABB {


    private static class arvore {
        Conta conta;
        arvore esq, dir;
    }

    public arvore root = null;

    public arvore insert(arvore p, Conta conta) {
        if (p == null) {
            p = new arvore();
            p.conta = conta;
            p.dir = null;
            p.esq = null;
        } else if (conta.getSaldo() < p.conta.getSaldo())
            p.esq = insert(p.esq, conta);
        else
            p.dir = insert(p.dir, conta);
        return p;
    }

    public void listarEmOrdem(arvore p) {
        if (p != null) {
            listarEmOrdem(p.dir);
            System.out.println(p.conta.toString());
            listarEmOrdem(p.esq);
        }
    }

    public Conta consultar(arvore p, String cpf) {
        if (p == null) {
            return null;
        }

        int comparacao = cpf.compareTo(p.conta.getCpfCnpj());

        if (comparacao == 0) {
            return p.conta;
        } else if (comparacao < 0) {
            return consultar(p.esq, cpf); // Buscar na subárvore esquerda.
        } else {
            return consultar(p.dir, cpf); // Buscar na subárvore direita.
        }
    }

    public arvore atualizarSaldo(arvore p, int numeroConta, double saldo) {
        if (p == null) {
            return p;
        }

        if (numeroConta == p.conta.getNumeroConta()) {
            p.conta.setSaldo(saldo);
        } else if (numeroConta < p.conta.getNumeroConta()) {
            p.esq = atualizarSaldo(p.esq, numeroConta, saldo);
        } else {
            p.dir = atualizarSaldo(p.dir, numeroConta, saldo);
        }
        return p;
    }

    public int contaNos(arvore p, int cont) {

        if (p != null) {
            cont += 1;
            cont = contaNos(p.esq, cont);
            cont = contaNos(p.dir, cont);

        }
        return cont;
    }

    public int contasSaldo(arvore p, int cont, double saldo) {

        if (p != null) {
            if (p.conta.getSaldo() >= saldo) {
                cont += 1;
            }
            cont = contasSaldo(p.esq, cont, saldo);
            cont = contasSaldo(p.dir, cont, saldo);
        }
        return cont;
    }

    public arvore remover(arvore p, int numeroConta) {
        if (p == null) {
            return p;
        }
        if (numeroConta < p.conta.getNumeroConta()) {
            p.esq = remover(p.esq, numeroConta);
        } else if (numeroConta > p.conta.getNumeroConta()) {
            p.dir = remover(p.dir, numeroConta);
        } else {
            if (p.esq == null && p.dir == null) {
                return null;
            } else if (p.esq == null) {
                return p.dir;
            } else if (p.dir == null) {
                return p.esq;
            } else {

                p.conta = verificaSucessor(p.dir);

                p.dir = remover(p.dir, p.conta.getNumeroConta());
            }
        }
        return p;
    }

    private Conta verificaSucessor(arvore p) {
        Conta conta = p.conta;
        while (p.esq != null) {
            conta = p.esq.conta;
            p = p.esq;
        }
        return conta;
    }

    public List<Conta> listarContas(arvore p, double saldo, List<Conta> contas) {
        if (p != null) {
            contas = listarContas(p.esq, saldo, contas);
            if (p.conta.getSaldo() >= saldo) {
                int i = 0;
                while (i < contas.size() && p.conta.getSaldo() < contas.get(i).getSaldo()) {
                    i++;
                }
                contas.add(i, p.conta);
            }
            contas = listarContas(p.dir, saldo, contas);

        }
        return contas;
    }


}
