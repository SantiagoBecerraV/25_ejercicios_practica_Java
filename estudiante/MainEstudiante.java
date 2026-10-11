    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiante;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class MainEstudiante {
    Scanner scanner = new Scanner(System.in);
    
    public void iniciar(){
    
        System.out.println("\n==============================================");
        
        int cantidadEstudiantes = 0;
        do{
            System.out.print("Ingrese la cantidad de estudiantes a registrar: ");
            if (scanner.hasNextInt()){
                cantidadEstudiantes = scanner.nextInt();
                if (cantidadEstudiantes <= 0){
                    System.out.println("Error ingrese un numero mayor a cero!!");
                }
            }else{
                System.out.println("Error. Debe ingresar un numero entero valido");
                scanner.next();
            }
        }while(cantidadEstudiantes <= 0);
    
        scanner.nextLine();
        
        
        /*
        PEDIR EXPLICACION DE ESTA LINEA
        */
        Curso curso = new Curso();
        
        for(int i = 0; i < cantidadEstudiantes; i++){
            System.out.println("Registre al estudiante #"+(i + 1));
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
           Estudiante est = new Estudiante(nombre);
            
            for(int j = 0; j < 3; j++){
                double notas = -1.0;
                do{
                    System.out.println("Ingrese la nota #"+(j+1)+" de (0-10): ");
                    if(scanner.hasNextDouble()){
                        notas = scanner.nextDouble();
                        if(notas > 10.0 || notas < 0.0){
                        System.out.println("Error. Ingrese un numero Mayor a 0.0 y Menor a 10.0");
                        }
                    }else{
                        System.out.println("Error. Ingrese un numero valido!");
                        scanner.next();
                    }
                }while(notas < 0.0 || notas > 10.0);
                
                est.agregarAsignatura(new Asignatura("Asignatura " + (j + 1), notas));
            }
            scanner.nextLine();
            System.out.print("Ingrese el numero de faltas: ");
            int faltas = scanner.nextInt();
            scanner.nextLine();
            est.setFaltas(faltas);
            curso.agregarEstudiante(est);
        }
        

        System.out.println("\n=== RESULTADOS DEL GRUPO ===");
        System.out.printf("Promedio general del grupo: %.2f\n", curso.calcularPromedioGeneral());
        System.out.println("Cantidad de aprobados: " + curso.contarAprobados());
        System.out.println("Cantidad de reprobados: " + curso.contarReprobados());

        Estudiante mejor = curso.getMejorEstudiante();
        Estudiante peor = curso.getPeorEstudiante();
        System.out.printf("Estudiante con mayor promedio: %s (%.2f)\n", mejor.getNombre(), mejor.getPromedio());
        System.out.printf("Estudiante con menor promedio: %s (%.2f)\n", peor.getNombre(), peor.getPromedio());
        
        System.out.print("Nombre del estudiante a buscar: ");
        String buscado = scanner.nextLine();
        Estudiante encontrado = curso.buscarEstudiante(buscado);
        if (encontrado != null) {
            System.out.printf("Encontrado: %s (%.2f)%n", encontrado.getNombre(), encontrado.getPromedio());
        } else {
            System.out.println("No se encontro al estudiante.");
        }
        curso.guardarReporte("reporte_curso.txt");
        System.out.println("Reporte guardado en reporte_curso.txt");
    }
}
