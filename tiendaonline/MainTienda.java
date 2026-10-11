/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tiendaonline;

import java.util.Scanner;

/**
 *
 * @author admin
 */

public class MainTienda {
    Scanner scanner = new Scanner(System.in);
    Carrito carrito;
    Cliente cliente;
    Inventario inventario = new Inventario();
    
    public static void main(String[] args) {
        MainTienda tienda = new MainTienda();
        tienda.inicioDeSesion();
        tienda.mostrarMenu();
        tienda.scanner.close();
    }
    
    public MainTienda() {
        inventario.agregarProducto(new Producto("Camiseta", 25000, 10, "ropa"));
        inventario.agregarProducto(new Producto("Pantalon", 50000, 20, "ropa"));
        inventario.agregarProducto(new Producto("Zapatos", 100000, 5, "calzado"));
        inventario.agregarProducto(new ProductoAlimenticio("Leche", 4000, 67, "2026-11-11"));
        inventario.agregarProducto(new ProductoElectronico("Teclado", 67000, 17, 14));
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n==== TIENDA BUNUELITO =====");
            System.out.println("1. Ver productos");
            System.out.println("2. Comprar productos");
            System.out.println("3. Ver factura");
            System.out.println("4. Salir\n");
            System.out.print("Seleccione una opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error!! Debe ingresar un numero.");
                scanner.next();
                System.out.print("Seleccione una opcion: ");
            }

            opcion = scanner.nextInt();
            
            switch  (opcion) {
                case 1:
                    verProductos();
                    break;
                case 2:
                    comprarProducto();
                    break;
                case 3:
                    carrito.mostrarFactura();
                    break;
                case 4:
                    System.out.println("Gracias por su compra.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
       }while (opcion != 4);
    }
    
    private void verProductos() {
        System.out.println("\n========= PRODUCTOS DISPONIBLES =========");
        for (int i = 0; i < inventario.cantidadProductos(); i++) {
            Producto p = inventario.getProducto(i);
            
            System.out.println((i + 1) + ". " + p.getNombre()
                + " [" + p.getCategoria() + "]"
                + " - $" + p.getPrecio()
                + " (stock: " + p.getStock() + ")");
            
            if (p instanceof ProductoAlimenticio) { 
                ProductoAlimenticio alimento = (ProductoAlimenticio) p;
                System.out.println("| caduca: " + alimento.getFechaCaducidad());
            }
            
            if (p instanceof ProductoElectronico) { 
                ProductoElectronico dispositivo = (ProductoElectronico) p;
                System.out.println("| garantia de : " + dispositivo.getMesesGarantia());
            }           
            System.out.println();
        }
    }

    private void comprarProducto() {
        System.out.print("Categoria (Ropa / Calzado / Electronicos / Alimentos / todas): ");
        String categoria = scanner.next();

        Producto[] filtrados = filtrarPorCategoria(categoria);
        if (filtrados.length == 0) {
            System.out.println("No hay productos en esa categoria.");
            return;
        }

        System.out.println("\n--- PRODUCTOS (" + categoria + ") ---");
        for (int i = 0; i < filtrados.length; i++) {
            System.out.println((i + 1) + ". " + filtrados[i].getNombre()
                + " - $" + filtrados[i].getPrecio()
                + " (stock: " + filtrados[i].getStock() + ")");
        };
        System.out.print("Numero del producto: ");
        int num = scanner.nextInt();

        if (num < 1 || num > filtrados.length) {
            System.out.println("Producto no existe.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
        } else if (carrito.agregarProducto(filtrados[num - 1], cantidad)) {
            System.out.println("Producto agregado al carrito.");
        } else {
            System.out.println("No hay stock suficiente.");
        }
    }

    private Producto[] filtrarPorCategoria(String categoria){
        int cantidad = 0;
        for (Producto p : inventario.getProductos()) {
            if (categoria.equalsIgnoreCase("todas") || p.getCategoria().equalsIgnoreCase(categoria)) {
                cantidad ++;
            }
        }
        
        Producto[] resultado = new Producto[cantidad];
        int i = 0;
        for (Producto p : inventario.getProductos()){
            if(categoria.equalsIgnoreCase("todas") || p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado[i] = p;
                i++;
            }
        }
        return resultado;
    }
    
    private void inicioDeSesion(){
        System.out.println("\n===========================");
        System.out.println("      INICIO DE SESION       ");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        while(!email.contains("@")){
            System.out.println("El email debe tener @");
            System.out.print("Email: ");
            email = scanner.nextLine();
        }
        
        cliente = new Cliente(nombre, email);
        carrito = new Carrito(cliente, inventario);
        System.out.println("Ingresando a la tienda.... Bienvenido "+ nombre);
    }
}
