/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;

/**
 *
 * @author admin
 */
public class CuentaCorriente extends CuentaBancaria {
    private double limiteSobregiro;

    public CuentaCorriente(int idTitular, String titular, double saldo, String pin, double limiteSobregiro) {
        super(idTitular, titular, saldo, pin);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo + limiteSobregiro) {
            return false;
        }
        saldo -= monto;
        cantidadRetiros++;
        totalRetirado += monto;
        registrarMovimiento("Retiro: -$" + monto);
        return true;
    }
}
