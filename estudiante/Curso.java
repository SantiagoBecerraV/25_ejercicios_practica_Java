/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Curso {
    private ArrayList<Estudiante> estudiantes;
    
    public Curso(){
        estudiantes = new ArrayList<>();
    }
    
    public void agregarEstudiante(Estudiante pEstudiante){
        estudiantes.add(pEstudiante);
    }
    
    public double calcularPromedioGeneral(){
        if (estudiantes.isEmpty()){
            return 0.0;
        }
        double suma = 0.0;
        for (Estudiante e : estudiantes){
            suma += e.getPromedio();
        }
        return suma / estudiantes.size();
    }
    
    public int contarAprobados(){
        int cantidad = 0;
        for (Estudiante e : estudiantes){
            if (e.cumpleRequisitos()){
                cantidad++;
            } 
        }
        return cantidad;
    }
    
    public ArrayList<String> getNombreAprobados(){
        ArrayList<String> nombres = new ArrayList<>();
        for (Estudiante e : estudiantes){
            if (e.cumpleRequisitos()) {
                nombres.add(e.getNombre());
            }
        }
        return nombres;
    }
    
    public int contarReprobados(){
        return estudiantes.size() - contarAprobados();
    }
    
    public Estudiante getMejorEstudiante(){
        if (estudiantes.isEmpty()){
            return null;
        }
        Estudiante mejor = estudiantes.get(0);
        for (Estudiante e : estudiantes){
            if (e.getPromedio() > mejor.getPromedio()){
                mejor = e;
            }
        }
        return mejor;
    }
    
    public Estudiante getPeorEstudiante(){
        if (estudiantes.isEmpty()){
            return null;
        }
        Estudiante peor = estudiantes.get(0);
        for (Estudiante e : estudiantes){
            if (e.getPromedio() < peor.getPromedio()){
                peor = e;
            }
        }
        return peor;
    }
    
    public void guardarReporte(String nombreArchivo){
        try (PrintWriter writer = new PrintWriter(nombreArchivo)){
            writer.println("=== Listado De Estudiantes ===");
            for (Estudiante e : estudiantes){
                writer.printf("%s - Promedio: %.2f - Faltas: %d%n",
                        e.getNombre(), e.getPromedio(), e.getFaltas());
            }
            
            writer.println();
            writer.println("=== ESTADISTICAS DEL CURSO ===");
            writer.printf("Promedio general: %.2f%n", calcularPromedioGeneral());
            writer.println("Cantidad de aprobados: " + contarAprobados());
            writer.println("Cantidad de reprobados: " + contarReprobados());

            if (!estudiantes.isEmpty()) {
                writer.printf("Mejor estudiante: %s (%.2f)%n",
                        getMejorEstudiante().getNombre(), getMejorEstudiante().getPromedio());
                writer.printf("Peor estudiante: %s (%.2f)%n",
                        getPeorEstudiante().getNombre(), getPeorEstudiante().getPromedio());
            }
        }catch (IOException e){
            System.out.println("Error al guardar el listado: "+ e.getMessage());
        }          
    }
    
        public Estudiante buscarEstudiante(String nombre){
        for (Estudiante e : estudiantes){
            if (e.getNombre().equalsIgnoreCase(nombre)){
                return e;
            }
        }
        return null;
    }
    
    public boolean modificarNota(String nombreEstudiante, String nombreAsignatura, double nuevaNota ){
        Estudiante e = buscarEstudiante(nombreEstudiante);
        if (e == null){
            return false;
        }
        for (Asignatura a : e.getAsignaturas()){
            if(a.getNombre().equalsIgnoreCase(nombreAsignatura)){
                a.setNotaFinal(nuevaNota);
                e.calcularPromedio();
                return true;
            }
        }
        return false;
    }
}
