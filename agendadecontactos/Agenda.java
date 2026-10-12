/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agendadecontactos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Agenda {
    private int longitudTelefono = 10;          
    private String archivo = "contactos.txt"; 
    private String separador = ";";

    private ArrayList<Contacto> contactos = new ArrayList<>();

    private int buscarIndice(String nombre) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private boolean telefonoValido(String telefono) {
        return telefono.matches("\\d{" + longitudTelefono + "}");
    }

    private boolean emailValido(String email) {
        int arroba = email.indexOf('@');
        int punto = email.lastIndexOf('.');
        return arroba > 0 && punto > arroba + 1 && punto < email.length() - 1;
    }

    private boolean contieneSeparador(Contacto c) {
        return c.getNombre().contains(separador)
                || c.getEmail().contains(separador)
                || c.getGrupo().contains(separador);
    }

    public boolean agregarContacto(Contacto c) {
        if (c.getNombre().isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return false;
        }

        if (!telefonoValido(c.getTelefono())) {
            System.out.println("El telefono debe tener solo numeros y exactamente "
                    + longitudTelefono + " digitos.");
            return false;
        }

        if (!emailValido(c.getEmail())) {
            System.out.println("El email debe contener un '@' y un '.'.");
            return false;
        }

        if (contieneSeparador(c)) {
            System.out.println("Los datos no pueden contener el caracter '" + separador + "'.");
            return false;
        }

        for (Contacto existente : contactos) {
            if (existente.getNombre().equalsIgnoreCase(c.getNombre())
                    || existente.getTelefono().equals(c.getTelefono())) {
                System.out.println("Ya existe un contacto con ese nombre o telefono.");
                return false;
            }
        }

        contactos.add(c);
        System.out.println("Contacto agregado.");
        return true;
    }

    public ArrayList<Contacto> buscarContacto(String texto) {
        ArrayList<Contacto> resultados = new ArrayList<>();
        String buscado = texto.toLowerCase();

        for (Contacto c : contactos) {
            if (c.getNombre().toLowerCase().contains(buscado)) {
                resultados.add(c);
            }
        }
        return resultados;
    }

    public void mostrarTodos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos para mostrar.");
            return;
        }

        for (Contacto c : contactos) {
            System.out.println("----------------------");
            c.mostrarInfo();
        }
        System.out.println("----------------------");
        System.out.println("Total: " + contactos.size());
    }

    public void mostrarContactosPorGrupo(String grupo) {
        int encontrados = 0;

        for (Contacto c : contactos) {
            if (c.getGrupo().equalsIgnoreCase(grupo)) {
                System.out.println("----------------------");
                c.mostrarInfo();
                encontrados++;
            }
        }

        if (encontrados == 0) {
            System.out.println("No hay contactos en el grupo '" + grupo + "'.");
        } else {
            System.out.println("----------------------");
            System.out.println("Total en el grupo: " + encontrados);
        }
    }

    public boolean eliminarContacto(String nombre) {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return false;
        }

        int i = buscarIndice(nombre);
        if (i == -1) {
            System.out.println("Contacto inexistente.");
            return false;
        }

        contactos.remove(i); 
        System.out.println("Contacto eliminado.");
        return true;
    }

    public boolean guardarEnArchivo() {
        try (BufferedWriter escritor =
                     Files.newBufferedWriter(Paths.get(archivo), StandardCharsets.UTF_8)) {
            for (Contacto c : contactos) {
                escritor.write(c.getNombre() + separador + c.getTelefono() + separador
                        + c.getEmail() + separador + c.getGrupo());
                escritor.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar la agenda: " + e.getMessage());
            return false;
        }
    }

    public int cargarDesdeArchivo() {
        Path ruta = Paths.get(archivo);

        if (!Files.exists(ruta)) {
            return 0;
        }

        ArrayList<Contacto> cargados = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                //ME TOCO PEDIRLE AYUDA A LA IA PARA EVITAR ERRROR CON CARACTERES ESPECIALES
                String[] partes = linea.split(java.util.regex.Pattern.quote(separador), -1);
                if (partes.length == 4) {
                    cargados.add(new Contacto(partes[0], partes[1], partes[2], partes[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar la agenda: " + e.getMessage());
            return contactos.size();
        }

        contactos = cargados; 
        return contactos.size();
    }
}
