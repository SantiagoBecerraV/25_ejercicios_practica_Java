/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;

/**
 *
 * @author admin
 */
public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    
    public Producto(String pNombre, double pPrecio, int pStock, String pCategoria){
        nombre = pNombre;
        precio = pPrecio;
        stock = pStock;
        categoria = pCategoria;
    }

    public String getCategoria() {
        return categoria;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public int getStock(){
        return stock;
    }
    
    public boolean hayStock(int cantidad) {
        return cantidad > 0 && stock >= cantidad;
    }
    
    public void disminuirStock(int cantidad) {
        if (hayStock(cantidad)) {
            stock -= cantidad;
        }
    }   
}
