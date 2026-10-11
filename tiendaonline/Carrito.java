/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;

/**
 *
 * @author admin
 */
public class Carrito {
    private Producto[] productos = new Producto[50];
    private int[] cantidades = new int[50];
    private int total = 0;
    private Cliente cliente;
    private Inventario inventario;

    public Carrito(Cliente pCliente, Inventario pInventario) {
        cliente = pCliente;
        inventario = pInventario;
    }
    
    private double montoMinimoDescuento = 15000;
    private double porcentajeDescuento = 0.10;
    
    public boolean agregarProducto(Producto p, int cantidad) {
        if (total >= productos.length) {
            return false; // carrito lleno
        }
        if (!inventario.hayStock(p, cantidad)) {
            return false; 
        }
        inventario.disminuirStock(p, cantidad);
        productos[total] = p;
        cantidades[total] = cantidad;
        total++;
        return true;
    }
    
    public double calcularSubtotal(){
        double suma = 0;
        for (int i = 0; i < total; i++) {
            suma += productos[i].getPrecio() * cantidades[i];
        }
        return suma;
    }
    
    public double calcularDescuento(){
        double subtotal = calcularSubtotal();
        if (subtotal > montoMinimoDescuento){
            return subtotal *porcentajeDescuento;
        }
        return 0;
    }
        
    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void mostrarFactura() {
        System.out.println("\n===== FACTURA =====");
        System.out.println("A nombre del cliente: " +cliente.getNombre());
        System.out.println("Email Cliente: "+cliente.getEmail());
        for (int i = 0; i < total; i++) {
            double subtotalFactura = productos[i].getPrecio() * cantidades[i];
            System.out.println(productos[i].getNombre()
                + " x" + cantidades[i]
                + " = $" + subtotalFactura);
        }
        System.out.println("=================================");
        System.out.println("Subtotal: $" + calcularSubtotal());
        System.out.println("Descuennto de: "+ calcularDescuento());
        System.out.println("Total a pagar de: "+ calcularTotal());
    }
}