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
public class Banco {
    private List<CuentaBancaria> cuentas = new ArrayList<>();
    
    public void agregarCuenta(CuentaBancaria cuenta){
        cuentas.add(cuenta);
    }
    
    public CuentaBancaria buscarCuenta(String nombreDestino) {
        for (CuentaBancaria c : cuentas) {
            if (c.getTitular().equalsIgnoreCase(nombreDestino)) {
                return c;
            }
        }
        return null;
    }
    
    public CuentaBancaria buscarCuentaPorId(int id) {
        for (CuentaBancaria c : cuentas) {
            if (c.getIdTitular() == id) {
                return c;
            }
        }
        return null;
    }
    
    
    public boolean validarPin(String titular, String pin){
        CuentaBancaria c = buscarCuenta(titular);
        
        if (c == null){
            return false;
        }
        return c.getPin().equals(pin);
    }
    
    
}
