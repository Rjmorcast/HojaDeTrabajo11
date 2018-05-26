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
        System.out.println("From where to where do you want to find the shortest path?(0 to "+(n.ciudades.size()-1)+")");
        System.out.println("Here's the complete list of the cities:");
        for (int i =0;i<n.ciudades.size();i++){
            System.out.println(i+". "+n.ciudades.get(i).getLabel());
        }
            int start = stdin.nextInt();
            int end = stdin.nextInt();
        n.findShorthestPath(start, end);
        
    }
    
}
