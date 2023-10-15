package br.com.fiap.arvores;

import br.com.fiap.model.Conta;

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

    public Conta oferecerOferta(arvore p, double saldoMinimo) {
        Conta conta = null;
        if (p != null) {
            Conta contaEsq = oferecerOferta(p.esq, saldoMinimo);
            if (contaEsq != null && contaEsq.getSaldo() > saldoMinimo &&
                    (conta == null || contaEsq.getSaldo() > conta.getSaldo())) {
                conta = contaEsq;
            }
            if (p.conta.getSaldo() > saldoMinimo &&
                    (conta == null || p.conta.getSaldo() > conta.getSaldo())) {
                conta = p.conta;
            }
            Conta contaDir = oferecerOferta(p.dir, saldoMinimo);

            if (contaDir != null && contaDir.getSaldo() > saldoMinimo &&
                    (conta == null || contaDir.getSaldo() > conta.getSaldo())) {
                conta = contaDir;
            }
        }
        return conta;
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


}
