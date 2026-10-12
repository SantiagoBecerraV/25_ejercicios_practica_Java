/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.agendadecontactos;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class MainAgendaContactos {
    private String linea = "=========================================";
    private String sublinea = "-----------------------------------------";
    
    public static void main(String[] args) {
        MainAgendaContactos app = new MainAgendaContactos();
        app.mostrarMenu();
        app.scanner.close();
    }
    
    Scanner scanner = new Scanner(System.in);
    Agenda agenda = new Agenda();
    
    

    public void mostrarMenu() {
        int cargados = agenda.cargarDesdeArchivo();
        if (cargados > 0) {
            System.out.println("Se han cargado " + cargados + " contactos.");
        }

        int opcion;

        do {
            System.out.println("\n" + linea);
            System.out.println("        AGENDA CONTACTOS ");
            System.out.println(linea);
            System.out.println("1. Registrar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Mostrar contactos");
            System.out.println("4. Mostrar contactos por grupo");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Guardar agenda");
            System.out.println("7. Salir");
            System.out.println(sublinea);
            System.out.print("Seleccione una opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error!! Ingrese dato correcto.");
                scanner.next();
                System.out.print("Seleccione opcion valida: ");
            }

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarContacto();
                    break;
                case 2:
                    buscarContacto();
                    break;
                case 3:
                    agenda.mostrarTodos();
                    break;
                case 4:
                    mostrarPorGrupo();
                    break;
                case 5:
                    eliminarContacto();
                    break;
                case 6:
                    guardar();
                    break;
                case 7:
                    guardar();
                    System.out.println("Hasta Luego! Saliendo...");
                    break;
                default:
                    System.out.println("Error!! Ingrese una opcion valida...");
            }
        } while (opcion != 7);
    }

    public void registrarContacto() {
        scanner.nextLine(); 

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Telefono: ");
        String telefono = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Grupo (Familia, Trabajo, Amigos... o Enter para 'Sin grupo'): ");
        String grupo = scanner.nextLine().trim();
        if (grupo.isEmpty()) {
            grupo = "Sin grupo";
        }

        agenda.agregarContacto(new Contacto(nombre, telefono, email, grupo));
    }

    public void buscarContacto() {
        scanner.nextLine(); 
        System.out.print("Ingrese el nombre (o parte del nombre) a buscar: ");
        String texto = scanner.nextLine().trim();

        if (texto.isEmpty()) {
            System.out.println("Debe escribir algo para buscar.");
            return;
        }

        ArrayList<Contacto> resultados = agenda.buscarContacto(texto);

        if (resultados.isEmpty()) {
            System.out.println("No hay contactos que coincidan con '" + texto + "'.");
            return;
        }

        System.out.println("\nSe encontraron " + resultados.size() + " contacto(s):");
        for (Contacto c : resultados) {
            System.out.println("----------------------");
            c.mostrarInfo();
        }
    }

    public void mostrarPorGrupo() {
        scanner.nextLine();

        System.out.print("Ingrese el grupo: ");
        String grupo = scanner.nextLine().trim();

        if (grupo.isEmpty()) {
            System.out.println("Debe escribir un grupo.");
            return;
        }

        agenda.mostrarContactosPorGrupo(grupo);
    }

    public void eliminarContacto() {
        scanner.nextLine(); 

        System.out.print("Ingrese el nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine().trim();
        
    if (!confirmarEliminacion(nombre)) {
        System.out.println("Eliminacion cancelada.");
        return;
    }

        agenda.eliminarContacto(nombre);
    }
    
    //Funcionalidad que anadi por practicar para que se vea mas dinamico y mas senior como yo
    private boolean confirmarEliminacion(String nombre) {
        while (true) {
            System.out.print("Seguro que quieres eliminar a '" + nombre + "'? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            }
            if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            }

            System.out.println("Responde solo 's' (si) o 'n' (no).");
        }
    }   

        private void guardar() {
        if (agenda.guardarEnArchivo()) {
            System.out.println("Listo!! Agenda guardada correctamente");
        }
    }

}
