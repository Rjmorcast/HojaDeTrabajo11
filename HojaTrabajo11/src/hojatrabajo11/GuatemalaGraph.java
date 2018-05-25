/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojatrabajo11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dodi-
 */
public class GuatemalaGraph {
    
    private Graph guatemalaGraph;
    private ArrayList<Vertex> ciudades = new ArrayList<>();
    
    public GuatemalaGraph() throws IOException  {
        readFile();
    }
    
    public void readFile() throws FileNotFoundException, IOException{
         File f;
        FileReader fr;
	BufferedReader br;
        //=============================Ingresar la oracion con archivo de texto==============
           f = new File("./guateGrapho.txt");
           fr = new FileReader(f);
           br = new BufferedReader(fr);
           String linea;
           int pos; int distance;
           String city ;
           String city2;
           while((linea= br.readLine())!= null){
                pos = 0; distance = 0;
                city =""; city2="";
                for(int i=0;i<linea.length();i++){
                    if (!" ".equals(linea.substring(i, i+1))){
                        if (pos == 0){
                            city = city + linea.substring(i, i+1);
                        } else if (pos==1){
                            city2 = city2 + linea.substring(i, i+1);
                        } else {
                            distance = Integer.parseInt(linea.substring(i, linea.length()));
                            break;
                        }
                    } else {pos++;}
                }
               System.out.println(city);
               System.out.println(city2);
               System.out.println(distance);
               addConection(city, city2, distance);
           }
           
           br.close();
           fr.close();
           System.out.println(getRelationShip("Capital", "Jocotillo"));
    }
    
    public void addConection(String name, String neighborName, int distance){
        Boolean n;
        //Verifica si existe la ciudad 1
        Vertex city = new Vertex(name);
        if (!guatemalaGraph.containsVertex(city)){
            n =  guatemalaGraph.addVertex(city, true);
            ciudades.add(city);
            System.out.println("No encontrado");
        }
        //Verifica si existe la ciudad 2
        Vertex otherCity = new Vertex(neighborName);
        if (!guatemalaGraph.containsVertex(otherCity)){
            n =  guatemalaGraph.addVertex(otherCity, true);
            ciudades.add(otherCity);
            System.out.println("No encontrado");
        }
        //Hacer la conexion entre las 2 
        n = guatemalaGraph.addEdge(city, otherCity, distance);
        
    }
    //Se llama a la funcion cuando hay una interrupcion del camino entre 2 ciudades
    public void interrupt(String name, String neighborName){
        Boolean n;
        Vertex city = new Vertex(name);
        Vertex city2 = new Vertex(neighborName);
        if ((guatemalaGraph.containsVertex(city)) && (guatemalaGraph.containsVertex(city2))){
           guatemalaGraph.removeEdge(new Edge(city, city2));
        }
    }
    
    
    public int getRelationShip (String city1, String city3){
         Vertex city = new Vertex(city1);
         Vertex city2 = new Vertex(city3);
         if ((guatemalaGraph.containsVertex(city)) && (guatemalaGraph.containsVertex(city2))){
             for (Edge x: guatemalaGraph.getEdges()){
                 if ((x.getOne()== city) && (x.getTwo()== city2)){
                     return x.getWeight();
                 } else if ((x.getOne()== city2) && (x.getTwo()== city)){ 
                         return x.getWeight();
                    } else {return 999999;}    
             }
        } else return 0;
         return 0;
    }
    
}
