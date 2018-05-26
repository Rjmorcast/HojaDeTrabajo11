/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojatrabajo11;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class HojaTrabajo11 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner stdin = new Scanner(System.in);
        GuatemalaGraph n = new GuatemalaGraph();
        boolean x = true;
        while (x){
            System.out.println("Ingrese una opcion");
            System.out.println("1. Ruta más corta entre ciudades");
            System.out.println("2. Nombre de la ciudad que queda en el centro del grafo");
            System.out.println("3. Indicar interrumpción de trafico entre un par de ciudades");
            System.out.println("4. Establecer conexión entre ciudad1 y ciudad2 con Km de distancia");
            System.out.println("5. Terminar el programa");
            int op = stdin.nextInt();
            if(op == 1){
                 System.out.println("De que ciudad a que ciudad desea encontrar el camino mas corto?(0 to "+(n.ciudades.size()-1)+")");
                 System.out.println("Lista de ciudades:");
            for (int i =0;i<n.ciudades.size();i++){
                System.out.println(i+". "+n.ciudades.get(i).getLabel());
            }   
            System.out.println("Ingrese primera ciudad");
            int start = stdin.nextInt();
            System.out.println("Ingrese segunda ciudad");
            int end = stdin.nextInt();
            n.findShorthestPath(start, end);
            }
            if(op == 2){
                
            }
            if(op == 3){
                 System.out.println("Lista de ciudades:");
                for (int i =0;i<n.ciudades.size();i++){
                    System.out.println(i+". "+n.ciudades.get(i).getLabel());
                }   
                System.out.println("Ingrese primera ciudad");
                String ciudad1 = stdin.next();
                System.out.println("Ingrese segunda ciudad");
                String ciudad2 = stdin.next();
                n.interrupt(ciudad1, ciudad2);
                System.out.println("Se ha realizado la interrupción con exito");
            }
            if(op==4){
                System.out.println("Ingrese primera ciudad");
                String ciudad1 = stdin.next();
                System.out.println("Ingrese segunda ciudad");
                String ciudad2 = stdin.next();
                System.out.println("Ingrese la cantidad de km");
                int km = stdin.nextInt();
                n.addConection(ciudad1, ciudad2, km);
            }
            if(op==5){
                System.out.println("Gracias por utilizar nuestro programa");
                x=false;
            }
            if((op>5) || (op<1)){
                System.out.println("Ingreso una opción no valida, intente de nuevo");
            }
            }
        }    
 }
    

