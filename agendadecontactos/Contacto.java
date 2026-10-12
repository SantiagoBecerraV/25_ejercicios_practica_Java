/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agendadecontactos;

/**
 *
 * @author admin
 */
public class Contacto {
    private String nombre;
    private String telefono;
    private String email;
    private String grupo;

    public Contacto(String nombre, String telefono, String email, String grupo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getGrupo() {
        return grupo;
    }

    public void mostrarInfo() {
        System.out.println("Nombre:   " + nombre);
        System.out.println("Telefono: " + telefono);
        System.out.println("Email:    " + email);
        System.out.println("Grupo:    " + grupo);
    }

    @Override
    public String toString() {
        return nombre + " | " + telefono + " | " + email + " | " + grupo;
    }
}
