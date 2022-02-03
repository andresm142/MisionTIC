from math import ceil
from math import sqrt
import csv
a=35600
b=6800
c=59300
d=24200
e=7400
n=0
lista_departamento=[]
datos=[]
dep_menor=0
dep_mayor=0
antenas={0:'a',1:"b",2:"c",3:"d",4:"e"}

# Lectura del arcivo y creacion de la lista con los datos
def leerCSV():
    global matriz
    archivo =  open('data.csv', mode='r', encoding='utf-8-sig' ) 
    lector = csv.reader(archivo) #Retorna un objeto con las filas del csv
   
    matriz=[]
    for fila in lector:             #Creo una matriz con los datos del archivo
        matriz.append(fila)  
        
    return matriz

# Funcion crear lista de departamentos
def crearlista(x):
    for i in range(len(x)):
        lista_departamento.append([x[i],0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0])
        
#Funcion de busqueda y creacion de lista de resultado
def buscar(valor):
    area_mayor=0
    area_menor=0
    antenas_menor=0
    antenas_mayor=0
    temp1=True
    temp2=True
    for i in range(len(matriz)):
        if matriz[i][0]==valor:
            entrada=matriz[i]
            
            #Se establecen condicones para el primer numero menor
            if temp1==True:
                area_menor=float(entrada[2])
                temp1=False
            if temp2==True:
                antenas_menor=int(entrada[3])
                temp2=False
            
            #entrada=['1', 'Amazonas', '1744400', '1', 'a'] 
            calcular_antenas(int(entrada[2]),int(entrada[3]),entrada[4])
            
            #Calculo area menor y mayor
            if area_menor>float(entrada[2]):
                area_menor=float(entrada[2])
            
            if area_mayor<float(entrada[2]):
                area_mayor=float(entrada[2])
            
            #Calculo antena menor y mayor
            if antenas_menor>int(entrada[3]):
                antenas_menor=int(entrada[3])

            if antenas_mayor<int(entrada[3]):
                antenas_mayor=int(entrada[3])
            
            #Creacion de la lista con sus datos por departamentos                    
            for z in range(len(lista_departamento)):
                
                if lista_departamento[z][0]==valor:
                    # print("entro a si", valor)    
                    lista_departamento[z]=[valor,                                          #0 idDep 
                                           entrada[1],                                     #1 Nombre departamento
                                           float(entrada[2])+lista_departamento[z][2],     #2 Aarea total
                                           int(entrada[3])+lista_departamento[z][3],       #3 Antenas previas
                                                resultado[0]+lista_departamento[z][4],     #4 Numero de nuevas antenas
                                                lista_departamento[z][5]+1,                #5 Cantidad de terrenos
                                                area_menor,                  #6 Area minima previas
                                                area_mayor,                  #7 Area maxima previas
                                                antenas_menor,                  #8 Minimo antenas previas
                                                antenas_mayor,                  #9 Maximo antenas previas
                                                resultado[1]+lista_departamento[z][10],    #10 Tipo A
                                                resultado[2]+lista_departamento[z][11],    #11 Tipo B
                                                resultado[3]+lista_departamento[z][12],    #12 Tipo C
                                                resultado[4]+lista_departamento[z][13],    #13 Tipo D
                                                resultado[5]+lista_departamento[z][14],    #14 Tipo E
                                                lista_departamento[z][15],                 #Promedio area
                                                lista_departamento[z][16],                 #Promedio antenas previas
                                                lista_departamento[z][17],                 #Desviación estándar muestral del área 
                                                lista_departamento[z][18]]                 #Desviación estándar muestral de la cantidad de antenas previas
          
    return lista_departamento

#Funcion para calcular nuemero de antenas y tipos
def calcular_antenas(area,cantidad,tipo):              
    global resultado
    resultado=[0,0]
    TipoA=0
    TipoB=0
    TipoC=0
    TipoD=0
    TipoE=0
    
    AreaAntenasInstaladas=cantidad*18400
    if tipo=="a":
        NumeroAntenas=ceil((area-AreaAntenasInstaladas)/a)
        if NumeroAntenas<0:
            NumeroAntenas=0
        TipoA=TipoA+NumeroAntenas
        
    elif tipo=="b":
        NumeroAntenas=ceil((area-AreaAntenasInstaladas)/b)
        if NumeroAntenas<0:
            NumeroAntenas=0
        TipoB=TipoB+NumeroAntenas
    elif tipo=="c":
        NumeroAntenas=ceil((area-AreaAntenasInstaladas)/c)
        if NumeroAntenas<0:
            NumeroAntenas=0
        TipoC=TipoC+NumeroAntenas
    elif tipo=="d":
        NumeroAntenas=ceil((area-AreaAntenasInstaladas)/d)
        if NumeroAntenas<0:
            NumeroAntenas=0
        TipoD=TipoD+NumeroAntenas
    elif tipo=="e":
        NumeroAntenas=ceil((area-AreaAntenasInstaladas)/e)
        if NumeroAntenas<0:
            NumeroAntenas=0
        TipoE=TipoE+NumeroAntenas
    else:
        NumeroAntenas=0
        
    resultado=[NumeroAntenas,TipoA,TipoB,TipoC,TipoD,TipoE]
    return resultado                    # Se retorna el numero de antenas y la cantidad segun tipo

#Funcion para calcular la desviacion estandar de la muestra
def dev_estandar(dep,prom,cant,tipo):
    suma=0
    for i in range(len(matriz)):
        if matriz[i][0]==dep:
            entrada=matriz[i]
            suma=(float(entrada[tipo])-prom)**2+suma
            # suma=suma**2
    # print("suma",suma)
    suma=(suma)/(cant-1)
    # print("suma al 2",suma)
    desviacion= float("{:.2f}".format(sqrt(suma)))
    # print(desviacion)
    return desviacion

idDep=input().split(" ")  #Lectura del id de los departamentos a analizar y organizados

for i in range(len(idDep)):
    idDep[i]=int(idDep[i])      #Se convierte cada elemento en int
    
idDep=sorted(idDep)             #Se ordena
for i in range(len(idDep)):
    idDep[i]=str(idDep[i])      ##Se vuelve a convertir cada elemento en str
    

crearlista(idDep)
leerCSV()

for i in range(len(idDep)):     #Por cada id de departamento se busca en la matriz principal
    buscar(idDep[i])
    
#Se establece el promedio y la desviacion estandar
for i in range(len(lista_departamento)):
    lista_departamento[i][15]=float("{:.2f}".format(lista_departamento[i][2]/lista_departamento[i][5]))     #Promedio areas
    lista_departamento[i][16]=float("{:.2f}".format(lista_departamento[i][3]/lista_departamento[i][5]))     #Promedio antenas previas
    lista_departamento[i][17]=dev_estandar(lista_departamento[i][0],lista_departamento[i][15],lista_departamento[i][5],2)   #Desviacion estandar area
    lista_departamento[i][18]=dev_estandar(lista_departamento[i][0],lista_departamento[i][16],lista_departamento[i][5],3)   #Desviacion estandar cantidad de antenas previas


# ----------------------------------SALIDAS---------------------------------------------
for i in range(len(lista_departamento)):
     print(lista_departamento[i][0],lista_departamento[i][1])
     print("terrain area")
     print("mean","{:.2f}".format(lista_departamento[i][15]))
     print("std","{:.2f}".format(lista_departamento[i][17]))
     print("min","{:.2f}".format(lista_departamento[i][6]))
     print("max","{:.2f}".format(lista_departamento[i][7]))
     print("total","{:.2f}".format(lista_departamento[i][2]))
     print("old antenna")
     print("mean","{:.2f}".format(lista_departamento[i][16]))
     print("std","{:.2f}".format(lista_departamento[i][18]))
     print("min",lista_departamento[i][8])
     print("max",lista_departamento[i][9])
     print("total",lista_departamento[i][3])
     print("new antenna")
     print("a",lista_departamento[i][10])
     print("b",lista_departamento[i][11])
     print("c",lista_departamento[i][12])
     print("d",lista_departamento[i][13])
     print("e",lista_departamento[i][14])

     
