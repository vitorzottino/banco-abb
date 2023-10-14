package br.com.fiap.arvores;

import br.com.fiap.model.Conta;

import java.util.ArrayList;

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
            return consultar(p.esq, cpf); // Buscar na subárvore esquerda.
        } else {
            return consultar(p.dir, cpf); // Buscar na subárvore direita.
        }
    }

}
