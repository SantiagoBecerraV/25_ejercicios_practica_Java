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
public class Main {
    public static void main(String[] args) {
        List<CuentaBancaria> cuentas = new ArrayList<>();
        Banco banco = new Banco();
        banco.agregarCuenta(new CuentaBancaria(1, "Bece", 500000, "1234"));
        banco.agregarCuenta(new CuentaBancaria(2, "Ana Lopez", 100000, "4321"));
        banco.agregarCuenta(new CuentaBancaria(3, "Carlos Ruiz", 250000, "1111"));

        Cajero cajero = new Cajero(banco);
        cajero.iniciar();
    }

}
