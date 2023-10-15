package br.com.fiap.arvores;

import br.com.fiap.model.Conta;

import java.util.ArrayList;

public class cpfABB {


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

    public ArrayList<Conta> contasAptas(arvore p, double saldo) {
        ArrayList<Conta> contas = new ArrayList<>();
        filtrarContas(p, saldo, contas);
        return contas;
    }

    private arvore filtrarContas(arvore p, double saldo, ArrayList<Conta> contasFiltradas) {
        if (p == null) {
            return null;
        }

        p.dir = filtrarContas(p.dir, saldo, contasFiltradas);
        if (p.conta.getSaldo() > saldo) {
            contasFiltradas.add(p.conta);
            return p.esq;
        }
        p.esq = filtrarContas(p.esq, saldo, contasFiltradas);

        return p;
    }

    public Conta consultar(arvore p, String cpf) {
        if (p == null) {
            return null;
        }

        int comparacao = cpf.compareTo(p.conta.getCpfCnpj());

        if (comparacao == 0) {
            return p.conta;
        } else if (comparacao < 0) {
            return consultar(p.esq, cpf);
        } else {
            return consultar(p.dir, cpf);
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


    public arvore removerConta(arvore p, int numeroConta) {
        if (p == null) {
            return p;
        }

        if (numeroConta < p.conta.getNumeroConta()) {
            p.esq = removerConta(p.esq, numeroConta);
        } else if (numeroConta > p.conta.getNumeroConta()) {
            p.dir = removerConta(p.dir, numeroConta);
        } else {
            if (p.esq == null) {
                return p.dir;
            } else if (p.dir == null) {
                return p.esq;
            } else {
                p.conta = menorSaldo(p.dir);
                p.dir = removerConta(p.dir, p.conta.getNumeroConta());
            }
        }
        return p;
    }

    private Conta menorSaldo(arvore p) {
        while (p.esq != null) {
            p = p.esq;
        }
        return p.conta;
    }


}
