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
public abstract class Cuenta {
    protected int idTitular;
    protected String titular;
    protected String pin;
    protected double saldo;
    protected List<String> historial = new ArrayList<>();

    public Cuenta(int idTitular, String titular, double saldo, String pin) {
        this.idTitular = idTitular;
        this.titular = titular;
        this.saldo = saldo;
        this.pin = pin;
    }

    public abstract void depositar(double monto);
    public abstract boolean retirar(double monto);

    protected void registrarMovimiento(String descripcion) {
        historial.add(descripcion);
        if (historial.size() > 5) {
            historial.remove(0);
        }
    }

    public List<String> getHistorial() { return historial; }
    public int getIdTitular() { return idTitular; }
    public String getTitular() { return titular; }
    public String getPin() { return pin; }
    public double getSaldo() { return saldo; }
}
