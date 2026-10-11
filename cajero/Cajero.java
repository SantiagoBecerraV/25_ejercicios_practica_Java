/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cajero;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Cajero {
    private Scanner sc = new Scanner(System.in);                       
    private Banco banco;
    private CuentaBancaria cuenta;
    
    public Cajero(Banco banco){
        this.banco = banco;
    }
    
    public void iniciar(){
        if(!verificarCliente()){
            return;
        }
        if(!validarPin()){
            return;
        }
        
        int opcion;
        
        do {
            System.out.println("\n ----- MENU CAJERO -----");
            System.out.println("1. Consultar Saldo\n"
                    + "2. Depositar\n"
                    + "3. Retirar\n"
                    + "4. Estadisticas\n"
                    + "5. Transferir\n"
                    + "6. Consultar Movimientos\n"
                    + "7. Salir");
            System.out.println("================================\n");
            
            while(!sc.hasNextInt()){
                System.err.println("La Opcion Debe Ser Un Numero.");
                sc.next();
                System.out.println("Selecciona una Opcion: ");
            }
            opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("================================");
                System.out.println("Su saldo es: " + cuenta.getSaldo());
                System.out.println("================================\n");
                break;
            case 2:
                System.out.println("================================");
                System.out.print("Ingrese el monto a depositar: ");
                double monto = sc.nextDouble();
                System.out.println("Deposito Realizado Correctamente");
                cuenta.depositar(monto);
                System.out.println("Nuevo saldo: "+cuenta.getSaldo());
                System.out.println("================================\n");
                break;
            case 3:
                System.out.println("================================");
                System.out.print("Cuanto desea Retirar: ");
                double montoRetirar = sc.nextDouble();
                System.out.println("================================\n");
                if(cuenta.retirar(montoRetirar)){
                    System.out.println("================================");
                    System.out.println("Retiro Exitoso");
                    System.out.println("Nuevo saldo: "+cuenta.getSaldo());
                    System.out.println("================================\n");
                }else{
                    System.out.println("================================");
                    System.out.println("Monto Invalido Verifique su Saldo..");
                    System.out.println("================================\n");
                }
                break;
            case 4:
                System.out.println("================================");
                cuenta.mostrarEstadisticas();
                System.out.println("================================\n");
                break;
            case 5:
                sc.nextLine();
                String nombreDestino = "";
                System.out.print("Nombre del titular del destino: ");
                nombreDestino = sc.nextLine().trim();
                
                CuentaBancaria destino = banco.buscarCuenta(nombreDestino);
                if (destino == null){
                    System.out.println("No existe una cuenta con ese titular...");
                    break;
                }
                if (destino == cuenta){
                    System.out.println("No puede transferirse a su propia cuenta..");
                    break;
                }
                
                System.out.print("Ingrese el monto a transferir: ");
                if (!sc.hasNextDouble()){
                    System.out.println("Error. Ingrese un dato valido!! ");
                    sc.next();
                    break;
                }
                double montoTransferencia = sc.nextDouble();
                
                if(cuenta.transferir(destino, montoTransferencia)){
                    System.out.println("Transferencia Realizada Correctamente a "+nombreDestino+"!! ");
                    System.out.println("Saldo restante: "+cuenta.getSaldo());
                    break;
                } else { 
                    System.out.println("Monto invalido o saldo insuficiente!! ");
                    break;
                }
            case 6:
                System.out.println("\n=================================");
                System.out.println("Sus Ultimos movimientos fueron: ");
                mostrarMovimientos();
                System.out.println("==================================");
                break;
            case 7:
                System.out.println("Hasta Luego...");
                return;
            default:
                System.out.println("Opciones Incorrectas");
                System.out.println("Seleccione una opcion del 1 al 6");
        }
    } while (opcion != 7);
        sc.close();
    }
  
    private void mostrarMovimientos(){
        List<String> movimientosHechos = cuenta.getHistorial();
        
        if (movimientosHechos.isEmpty()){
            System.out.println("No hay movimientos realizados registrados..");
        }else{
            for(String mov : movimientosHechos){
                System.out.println("- "+ mov);
            }
        }
    }
    
    private boolean verificarCliente(){
        System.out.println("\n============================================");
        System.out.print("Ingrese su titular o su Id: ");
        String nombre = sc.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("Error: La entrada está vacía.");
            return false;   
        }
        
        if (nombre.matches("\\d+")) {
            // Caso: Se ingresó un ID (Entero)
            cuenta = banco.buscarCuentaPorId(Integer.parseInt(nombre));
        } else {
            // Caso: se ingresó un nombre
            cuenta = banco.buscarCuenta(nombre);
        }

        if (cuenta == null){
            System.out.println("Cliente no encontrado.....");
            return false;
        }
        return true;
    }

         public boolean validarPin(){
        System.out.println("        Cajero Electronico       ");
        
        for(int i=1; i<=3;i++){
            System.out.print("Ingrese su pin: ");
            String pin = sc.next();
            
            if(banco.validarPin(cuenta.getTitular(), pin)){
                System.out.println("Acceso Permitido.");
                return true;
            }
            
            System.out.println("Pin Incorrecto");
            System.out.println("Intentos restantes: "+(3-i));
        }
        
        return false;
    }
}
