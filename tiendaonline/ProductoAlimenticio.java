/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;

/**
 *
 * @author admin
 */
public class ProductoAlimenticio extends Producto{
    private String fechaCaducidad;
    
    public ProductoAlimenticio(String pNombre, double pPrecio, int pStock, String pFechaCaducidad){
        super(pNombre, pPrecio, pStock, "Alimentos");
        fechaCaducidad = pFechaCaducidad;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

}
