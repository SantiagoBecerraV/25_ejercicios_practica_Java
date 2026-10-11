/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiante;

/**
 *
 * @author admin
 */
public class Asignatura {
    private String nombre;
    private double notaFinal;

    public Asignatura(String pNombre, double pNotaFinal) {
        nombre = pNombre;
        notaFinal = pNotaFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double pNotaFinal) {
        notaFinal = pNotaFinal;
    }
    
    
}
