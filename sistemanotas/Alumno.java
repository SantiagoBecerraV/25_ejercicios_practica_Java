/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemanotas;

/**
 *
 * @author admin
 */
public class Alumno {
    private double notaMaxima = 10.0;
    private double notaMinimaAprobar = 6.0;

    private String nombre;
    private double notaParcial1;
    private double notaParcial2;
    private double examenFinal;
    private double notaOriginal;
    private double notaFinal;

    public Alumno(String nombre, double notaParcial1, double notaParcial2, double examenFinal) {
        if (notaParcial1 < 0 || notaParcial1 > notaMaxima
                || notaParcial2 < 0 || notaParcial2 > notaMaxima
                || examenFinal < 0 || examenFinal > notaMaxima) {
            throw new IllegalArgumentException("Cada nota debe estar entre 0 y " + notaMaxima);
        }
        this.nombre = nombre;
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.examenFinal = examenFinal;
        this.notaOriginal = calcularNotaOriginal();
        this.notaFinal = notaOriginal;
    }

    public double calcularNotaOriginal() {
        return notaParcial1 * 0.30 + notaParcial2 * 0.30 + examenFinal * 0.40;
    }
    
    public String getNombre() {
        return nombre;
    }

    public double getNotaOriginal() {
        return notaOriginal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public boolean aplicarCurvaPorcentual(double porcentaje) {
        if (porcentaje <= 0) {
            return false;
        }
        double incremento = notaFinal * porcentaje / 100;
        notaFinal = Math.min(notaMaxima, notaFinal + incremento);
        return true;
    }

    public boolean aplicarCurvaPuntos(double puntos) {
        if (puntos <= 0) {
            return false;
        }
        notaFinal = Math.min(notaMaxima, notaFinal + puntos);
        return true;
    }
    
    public boolean estaAprobado() {
        return notaFinal >= notaMinimaAprobar;
    }

    public void mostrarInfo() {
        System.out.println("Alumno:        " + nombre);
        System.out.println("Nota original: " + notaOriginal);
        System.out.println("Nota final:    " + notaFinal);
        System.out.println("Estado:        " + (estaAprobado() ? "Aprobado" : "Reprobado"));
    }
}
