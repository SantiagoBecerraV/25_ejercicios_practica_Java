/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemanotas;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 *
 * @author admin
 */
public class MainSistemaNotas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    
        int n = leerEntero(sc, "Cuantos alumnos hay en el curso?: ");
        while (n <= 0) {
            n = leerEntero(sc, "Debe ser mayor a 0. Ingresa de nuevo: ");
        }
        
        ArrayList<Alumno> alumnos = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.print("Nombre del alumno " + i + ": ");
            String nombre = sc.nextLine();

            double p1 = leerNota(sc, "Nota del primer parcial de " + nombre + " (0 a 10): ");
            double p2 = leerNota(sc, "Nota del segundo parcial de " + nombre + " (0 a 10): ");
            double ef = leerNota(sc, "Nota del examen final de " + nombre + " (0 a 10): ");

            alumnos.add(new Alumno(nombre, p1, p2, ef));
        }
// Guardar quiénes estaban reprobados ANTES de la curv
        boolean[] reprobadoAntes = new boolean[n];
        for (int i = 0; i < n; i++) {
            reprobadoAntes[i] = !alumnos.get(i).estaAprobado();
        }
        
        double notaMasAlta = alumnos.get(0).getNotaOriginal();
        for (Alumno a : alumnos) {
            if (a.getNotaOriginal() > notaMasAlta) {
                notaMasAlta = a.getNotaOriginal();
            }
        }        

        double curva = 10 - notaMasAlta;
        System.out.println("\nNota mas alta: " + notaMasAlta);
        System.out.println("Curva a aplicar: +" + curva);

        for (Alumno a : alumnos) {
            a.aplicarCurvaPuntos(curva);
        }

        System.out.println("\n===== LISTADO DE ALUMNOS =====");
        for (Alumno a : alumnos) {
            String estado = a.estaAprobado() ? "APROBADO" : "REPROBADO";
            System.out.println(a.getNombre()
                    + " | Original: " + a.getNotaOriginal()
                    + " | Final: " + a.getNotaFinal()
                    + " | " + estado);
        }


        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getNotaFinal();
        }
        double media = suma / n;

        // Desviación estándar
        double sumaCuadrados = 0;
        for (Alumno a : alumnos) {
            double diferencia = a.getNotaFinal() - media;
            sumaCuadrados += diferencia * diferencia;
        }
        double desviacion = Math.sqrt(sumaCuadrados / n);

        // Alumnos que pasaron de reprobado a aprobado
        int pasaron = 0;
        for (int i = 0; i < n; i++) {
            if (reprobadoAntes[i] && alumnos.get(i).estaAprobado()) {
                pasaron++;
            }
        }

        System.out.println("\n===== ESTADISTICAS AVANZADAS =====");
        System.out.println("Media del curso (nota final): " + media);
        System.out.println("Desviacion estandar:          " + desviacion);
        System.out.println("Pasaron de reprobado a aprobado gracias a la curva: " + pasaron);

        sc.close();
    }
    
    public static double leerNota(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = sc.nextDouble();
                sc.nextLine();
                if (nota >= 0 && nota <= 10) {
                    return nota;
                }
                System.out.println("Error!! Debe estar entre 0 y 10.");
            } catch (InputMismatchException e) {
                System.out.println("Error!! Ingrese un dato valido.");
                sc.nextLine();
            }
        }
    }
    
    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Error!! Ingrese un dato valido.");
                sc.nextLine();
            }
        }
    }
}
