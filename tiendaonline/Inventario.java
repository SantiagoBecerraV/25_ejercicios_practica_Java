/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();
    
    public void agregarProducto(Producto p){
        productos.add(p);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public Producto getProducto(int indice){
        return productos.get(indice);
    }
    
    public int cantidadProductos(){
        return productos.size();
    }
    
    public boolean hayStock(Producto p, int cantidad) {
        return cantidad > 0 && p.getStock() >= cantidad;
    }

    public boolean disminuirStock(Producto p, int cantidad) {
        if (!hayStock(p, cantidad)) {
            return false;
        }
        p.disminuirStock(cantidad);
        return true;
    }
}
