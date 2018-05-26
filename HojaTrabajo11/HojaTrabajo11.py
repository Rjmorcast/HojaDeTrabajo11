#Hoja de trabajo 11
#Rodrigo Morales
#Andres Urizar 17632

import networkx as nx
G = nx.DiGraph()
#Abre archivo de texto
f = open('./guateGrapho.txt','r')
#Recorre el archivo, separando las ciudades y los kilometros entre ellos
for linea in f:
    pos = 0
    pos = linea.find(" ")
    ciudad1 = linea[:pos]
    pos1 = linea.find(" ",pos+1)
    ciudad2 = linea[pos+1:pos1]
    km = linea[pos1+1:]
    G.add_node(ciudad1)
    G.add_node(ciudad2)
    G.add_edge(ciudad1,ciudad2,weight=int(km))
    print ciudad1
    print ciudad2
    print km
        
f.close()
print ("Nodos: ",G.nodes()) #Muestra los nodos
print ("Aristas:",G.edges())#Muestra las relaciones o aristas
print()
ciudad = raw_input("Ingrese una de las ciudades")
print("Ruta mas corta")
print(nx.single_source_dijkstra_path(G,ciudad)) #muestra los caminos mas cortos
print()
print("Longitudes entre ciudades")
print(nx.single_source_dijkstra_path_length(G,ciudad)) #Muestra las 
