/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;

/**
 *
 * @author admin
 */
public class ProductoElectronico extends Producto {
    private int mesesGarantia;
            
    public ProductoElectronico(String pNombre, double pPrecio, int pStock, int pMesesGarantia) {
        super(pNombre, pPrecio, pStock, "Electronicos");
        mesesGarantia = pMesesGarantia;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(int mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    }
}