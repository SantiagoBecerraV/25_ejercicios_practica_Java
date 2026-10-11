/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CuentaBancaria extends Cuenta{
    protected int cantidadRetiros;
    protected int cantidadDepositos;
    protected double totalRetirado;
    protected double totalDepositado;

    public CuentaBancaria(int idTitular, String titular, double saldo, String pin) {
        super(idTitular, titular, saldo, pin);
    }
    
    //--------------------------------------------------------------
    //METODOS
    //--------------------------------------------------------------
    
    
    //Getters

    public int getCantidadRetiros() {
        return cantidadRetiros;
    }

    public int getCantidadDepositos() {
        return cantidadDepositos;
    }

    public double getTotalRetirado() {
        return totalRetirado;
    }

    public double getTotalDepositado() {
        return totalDepositado;
    }
    
    
    //Setters

    public void setCantidadRetiros(int cantidadRetiros) {
        this.cantidadRetiros = cantidadRetiros;
    }

    public void setCantidadDepositos(int cantidadDepositos) {
        this.cantidadDepositos = cantidadDepositos;
    }

    public void setTotalRetirado(double totalRetirado) {
        this.totalRetirado = totalRetirado;
    }

    public void setTotalDepositado(double totalDepositado) {
        this.totalDepositado = totalDepositado;
    }
    
    //Depositar dinero
    @Override
    public void depositar(double monto) {
        saldo += monto;
        cantidadDepositos++;
        totalDepositado += monto;
        registrarMovimiento("Depósito: +$" + monto);
    }
    
    @Override
    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }
        saldo -= monto;
        cantidadRetiros++;
        totalRetirado += monto;
        registrarMovimiento("Retiro: -$" + monto);
        return true;
    }
    
    public void mostrarEstadisticas(){
        System.out.println("\nEstadisticas Movimientos");
        System.out.println("- Cantidad Depositos: "+cantidadDepositos);
        System.out.println("- Cantidad Retiros: "+cantidadRetiros);
        System.out.println("- Total Depositado: "+totalDepositado);
        System.out.println("- Total Retirado: "+totalRetirado);
    }

    // METODO EJERCICIO 1
    public boolean transferir(CuentaBancaria cuentaDestino, double monto){
        if( monto <= 0 || monto > saldo ){
            return false;
        }
        retirar (monto);
        cuentaDestino.depositar(monto);
        return true;
    }
    
    // METODO EJERCICIO 2
    public void registroMovimientos (String movimiento){
        historial.add(movimiento);
        if (historial.size() > 5){
            historial.remove(0);        
        }  
    }

}
