/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojatrabajo11;

/**
 *
 * @author Dodi-
 */
// Arup Guha
// 6/25/02
// Floyd Warshall's algorithm: an example of dynamic programming.

import java.io.IOException;
import java.util.*;

public class floyd {

  	public static int[][] shortestpath(int[][] adj, int[][] path, int cantidadVertices) {

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

  	// Copies the contents of array b into array a. Assumes that both a and
  	// b are 2D arrays of identical dimensions.
  	public static void copy(int[][] a, int[][] b) {

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

  	public static void main(String[] args) throws IOException {

		Scanner stdin = new Scanner(System.in);

        GuatemalaGraph n = new GuatemalaGraph();
    	// Tests out algorithm with graph shown in class.
    	int[][] m = new int[n.ciudades.size()][n.ciudades.size()];
//    	m[0][0] = 0; m[0][1] = 3; m[0][2] = 8; m[0][3] = 10000; m[0][4] = -4;
//    	m[1][0] = 10000; m[1][1] = 0; m[1][2] = 10000; m[1][3] = 1;
//    	m[1][4]=7;
//    	m[2][0] = 10000; m[2][1] = 4; m[2][2] = 0; m[2][3] = 10000; 
//    	m[2][4] = 10000;
//    	m[3][0] = 2; m[3][1] = 10000; m[3][2] = -5; m[3][3] = 0; 
//    	m[3][4] = 10000;
//    	m[4][0] = 10000; m[4][1] = 10000; m[4][2] = 10000; m[4][3] = 6;
//    	m[4][4] =0;

        for (int x=0; x<n.ciudades.size();x++){
            for (int y=0;y<n.ciudades.size();y++){
                
                m[x][y]=n.getRelationShip(n.ciudades.get(x).getLabel(), n.ciudades.get(y).getLabel());
                
            }
        }

    	int[][] shortpath;
    	int[][] path = new int[n.ciudades.size()][n.ciudades.size()];
    
    	// Initialize with the previous vertex for each edge. -1 indicates
    	// no such vertex.
    	for (int i=0; i<n.ciudades.size(); i++)
    		for (int j=0; j<n.ciudades.size(); j++)
    			if (m[i][j] == 10000){
    				path[i][j] = -1;
                        }else{
    				path[i][j] = i;
                        }
    	// This means that we don't have to go anywhere to go from i to i.
    	for (int i=0; i<n.ciudades.size(); i++)
    		path[i][i] = i;
    	

    	shortpath = shortestpath(m, path, n.ciudades.size());

		// Prints out shortest distances.
         System.out.println("Solucion");
    	for (int i=0; i<n.ciudades.size();i++) {
      		for (int j=0; j<n.ciudades.size();j++)
        		System.out.print(shortpath[i][j]+" ");
      		System.out.println();
    	}
    
    	System.out.println("From where to where do you want to find the shortest path?(0 to 4)");
    	int start = stdin.nextInt();
    	int end = stdin.nextInt();
    
    	// The path will always end at end.
    	String myPath = n.ciudades.get(end).getLabel() + "";
    
    	// Loop through each previous vertex until you get back to start.
    	while (path[start][end] != start) {
    		myPath = n.ciudades.get(path[start][end]).getLabel() + " -> " + myPath;
    		end = path[start][end];
    	}
    	
    	// Just add start to our string and print.
    	myPath = n.ciudades.get(start).getLabel() + " -> " + myPath;
    	System.out.println("Here's the path "+myPath);
 
  	}
}