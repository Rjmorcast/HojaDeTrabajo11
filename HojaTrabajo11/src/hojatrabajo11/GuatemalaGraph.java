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
    public ArrayList<Vertex> ciudades = new ArrayList<>();
    
    public GuatemalaGraph() throws IOException  {
        guatemalaGraph = new Graph();
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
  
               
                addConection(city, city2, distance);
           }
           
           br.close();
           fr.close();
           System.out.println(getRelationShip("Jocotillo","Capital"));

    }
    
    public void addConection(String name, String neighborName, int distance){
        boolean n;
        //Verifica si existe la ciudad 1
        Vertex city = new Vertex(name);
        Vertex otherCity = new Vertex(neighborName);
        
        if (!guatemalaGraph.containsVertex(name)){
            n =  guatemalaGraph.addVertex(city, true);
            ciudades.add(city);
        }
        //Verifica si existe la ciudad 2
        
        if (!guatemalaGraph.containsVertex(neighborName)){
            n =  guatemalaGraph.addVertex(otherCity, true);
            ciudades.add(otherCity);
        }
        //Hacer la conexion entre las 2 
        n = guatemalaGraph.addEdge(city, otherCity, distance);
        
    }
    //Se llama a la funcion cuando hay una interrupcion del camino entre 2 ciudades
    public void interrupt(String name, String neighborName){
        Boolean n;
        Vertex city = new Vertex(name);
        Vertex city2 = new Vertex(neighborName);
        if ((guatemalaGraph.containsVertex(name)) && (guatemalaGraph.containsVertex(neighborName))){
           guatemalaGraph.removeEdge(new Edge(city, city2));
        }
    }
    
    
    public int getRelationShip (String city1, String city3){
         Vertex city = guatemalaGraph.getVertex(city1);
         Vertex city2 = guatemalaGraph.getVertex(city3);
         if (city1 == null ? city3 == null : city1.equals(city3)){
             
             return 0;
         }
         
         if ((guatemalaGraph.containsVertex(city1)) && (guatemalaGraph.containsVertex(city3))){
             for (Edge x: guatemalaGraph.getEdges()){
                 
                 //Verifica una direccion
                 
                 if ((x.getOne().getLabel() == null ? city2.getLabel() == null : x.getOne().getLabel().equals(city2.getLabel())) && (x.getTwo().getLabel() == null ? city.getLabel() == null : x.getTwo().getLabel().equals(city.getLabel()))){
                     
                     return x.getWeight();
                 }
                 
                //Verifica la otra direccion
                 if ((x.getOne().getLabel() == null ? city.getLabel() == null : x.getOne().getLabel().equals(city.getLabel())) && (x.getTwo().getLabel() == null ? city2.getLabel() == null : x.getTwo().getLabel().equals(city2.getLabel()))){
                     
                     
                     return x.getWeight();
                     
                 }  
             }
             
             return 10000;
        } else return 0;

//         for (Edge x: guatemalaGraph.getEdges()){
//                 System.out.println(x);
//                 System.out.println(x.getOne()+" 1");
//                 System.out.println(x.getTwo()+" 2");
//         }
//         return 0;
    }
    
//    Algoritmo tomado de http://www.cs.ucf.edu/~dmarino/ucf/java/floyd.java
//    
//    Adaptado por Rodrigo Morales
//    
//    
//    
    public  int[][] shortestpath(int[][] adj, int[][] path, int cantidadVertices) {

    	int n =  cantidadVertices;//n es la cantidad de vertices
    	int[][] ans = new int[n][n];
    
    	// Implement algorithm on a copy matrix so that the adjacency isn't
    	// destroyed.
    	copy(ans, adj);

    	// Compute successively better paths through vertex k.
    	for (int k=0; k<n;k++) {

      		// Do so between each possible pair of points.
      		for (int i=0; i<n; i++) {
        		for (int j=0; j<n;j++) {
        		
        
          			if (ans[i][k]+ans[k][j] < ans[i][j]) {
          				ans[i][j] = ans[i][k]+ans[k][j];
          				path[i][j] = path[k][j];
          			}
          		}
      		}
    	}
    
    	// Return the shortest path matrix.
    	return ans;
  	}
    
        public void copy(int[][] a, int[][] b) {

    	for (int i=0;i<a.length;i++)
      		for (int j=0;j<a[0].length;j++)
        		a[i][j] = b[i][j];
  	}

          	// Returns the smaller of a and b.
  	public static int min(int a, int b) {
    	if (a < b) 
      		return a;
    	else       
      		return b;
  	}
        
        
        public  void findShorthestPath(int start, int end){
        
            int[][] m = new int[ciudades.size()][ciudades.size()];


            for (int x=0; x<ciudades.size();x++){
                for (int y=0;y<ciudades.size();y++){

                    m[x][y]=getRelationShip(ciudades.get(x).getLabel(), ciudades.get(y).getLabel());

                }
            }

            int[][] shortpath;
            int[][] path = new int[ciudades.size()][ciudades.size()];

            // Initialize with the previous vertex for each edge. -1 indicates
            // no such vertex.
            for (int i=0; i<ciudades.size(); i++)
                    for (int j=0; j<ciudades.size(); j++)
                            if (m[i][j] == 10000){
                                    path[i][j] = -1;
                            }else{
                                    path[i][j] = i;
                            }
            // This means that we don't have to go anywhere to go from i to i.
            for (int i=0; i<ciudades.size(); i++)
                    path[i][i] = i;


            shortpath = shortestpath(m, path, ciudades.size());

                    // Prints out shortest distances.
//             System.out.println("Solucion");
//            for (int i=0; i<ciudades.size();i++) {
//                    for (int j=0; j<ciudades.size();j++)
//                            System.out.print(shortpath[i][j]+" ");
//                    System.out.println();
//            }
//            
            // The path will always end at end.
            String myPath = ciudades.get(end).getLabel() + "";
            int newEnd = end;
            // Loop through each previous vertex until you get back to start.
            while (path[start][end] != start) {
                    myPath = ciudades.get(path[start][end]).getLabel() + " -> " + myPath;
                    end = path[start][end];
            }

            // Just add start to our string and print.
            myPath = ciudades.get(start).getLabel() + " -> " + myPath;
            System.out.println("Este es el camino más corto "+myPath);
            System.out.println("Distancia total:" +shortpath[start][newEnd]);


        }
}
