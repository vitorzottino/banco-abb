package br.com.fiap.model;

public class Conta {

    private Cliente cliente;
    private int numeroConta;
    private String tipoConta;
    private double saldo;


    public Conta(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCpfCnpj(){
        return this.cliente.getCpfCnpj();
    }

    public String getNome(){
        return this.cliente.getNome();
    }
    @Override
    public String toString() {
        return "Conta{" +
                cliente.toString() +
                ", numeroConta=" + numeroConta +
                ", tipoConta='" + tipoConta + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
