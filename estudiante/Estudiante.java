/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.estudiante;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Estudiante {
    private String nombre;
    private ArrayList<Asignatura> asignaturas;
    private double promedio;
    private int faltas;
    
    public Estudiante(String pNombre){
        nombre = pNombre;
        asignaturas = new ArrayList<>();
        promedio = 0.0;
        faltas = 0;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getFaltas() {
        return faltas;
    }
    
    public void agregarAsignatura(Asignatura pAsignatura){
        asignaturas.add(pAsignatura);
        calcularPromedio();
    }
    
    public void calcularPromedio(){
        if (asignaturas.isEmpty()){
            promedio = 0.0;
            return;
        }
        
        double suma = 0.0;
        for (Asignatura a : asignaturas){
            suma += a.getNotaFinal();
        }
        promedio = suma / asignaturas.size();
            
    }
    
    public boolean cumpleRequisitos() {
        return promedio >= 6.0 && faltas < 10;
    }
    
    public boolean aprobo(){
        if (cumpleRequisitos()){
            System.out.println("Segun su promedio y faltas, ha aprobado!!");
            return true;
        }else{
            System.out.println("Segun su promedio y faltas, no ha aprobado");
            return false;
        }
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public double getPromedio(){
        return promedio;
    }
    
    public ArrayList<Asignatura> getAsignaturas(){
        return asignaturas;
    }
    
}
