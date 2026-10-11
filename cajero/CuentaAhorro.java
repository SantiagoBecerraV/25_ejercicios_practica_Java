/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;

/**
 *
 * @author admin
 */
public class CuentaAhorro extends CuentaBancaria {
    private static final double INTERES = 0.01; // 1% por depósito

    public CuentaAhorro(int idTitular, String titular, double saldo, String pin) {
        super(idTitular, titular, saldo, pin);
    }

    @Override
    public void depositar(double monto) {
        double interes = monto * INTERES;
        super.depositar(monto + interes);
        registrarMovimiento("Interés: +$" + interes);
    }
}
