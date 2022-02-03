from math import ceil
a=35600
b=6800
c=59300
d=24200
e=7400
# a=11400
# b=12600
# c=41100
# d=14700
# e=38000
n=0
lista_departamento=[]
dep_menor=0
dep_mayor=0
antenas={0:'a',1:"b",2:"c",3:"d",4:"e"}

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

# Funcion para buscar el departamento con menor y mayor numeros de antenas segun tipo
def buscar_departamento(a):
    global res_buscar_dep
    # Se escoge el primer departamento y su cantidad de antenas
    mayor=lista_departamento[0][a]
    menor=lista_departamento[0][a]
    dep_mayor=1
    dep_menor=1    
    
    for j in range(n):
        if (menor > lista_departamento[j][a]):
            
            menor=lista_departamento[j][a]          #Se reemplaza la variable con el nuevo valor menor
            #Se asigna el numero del departamento donde se encuentra
            dep_menor= j+1
            
        if (mayor < lista_departamento[j][a]):
            
            mayor=lista_departamento[j][a]          #Se reemplaza la variable con el nuevo valor meyor
            #Se asigna el numero del departamento donde se encuentra
            dep_mayor= j+1
    res_buscar_dep=[dep_menor,menor,dep_mayor,mayor]
    return res_buscar_dep

# Funcion para buscar en el departamento el tipo menor y mayor de antenas
def buscar_tipo_antenas(dept):
    tipomenor=0
    tipomayor=0
    # Se escoge el primer valor sugun departamento
    global tipo_antena
    valormenor=lista_departamento[dept][1]
    valormayor=lista_departamento[dept][1]
    
    for j in range(1,6):
        if (valormenor > lista_departamento[dept][j]):
            
            valormenor=lista_departamento[dept][j]          #Se reemplaza la variable con el nuevo valor menor
            #Se asigna el numero del indice donde se encuentra tipo menor
            tipomenor= j-1
            
        if (valormayor < lista_departamento[dept][j]):
            
            valormayor=lista_departamento[dept][j]          #Se reemplaza la variable con el nuevo valor meyor
              #Se asigna el numero del indice donde se encuentra
            tipomayor= j-1
    tipo_antena=[tipomenor,valormenor,tipomayor,valormayor]
    
    return tipo_antena

#Leer hasta que sean datos validos
while True:                     
    
    n,m=input().split(" ")   #Cantidad de departamentos y terrenos
    n=int(n)
    m=int(m)
    
    if n>=1:                    # Departamentos mayor o igual a 1
        break

# Se crea la lista con el total de departamentos    
for x in range(1,n+1):                      
    lista_departamento.append(x)
    lista_departamento[x-1]=[0,0,0,0,0,0]
    
# Lectura de los datos segun la cantidad de terrenos 
for i in range(m):                      
    while True:
        #Se leen departamento, area, antenas y tipo
        numDep,areaDep,cantAntenas,TipoAntena=input().split(" ")     
        numDep,areaDep,cantAntenas=int(numDep),eval(areaDep),int(cantAntenas)
        
        # Si el departamento ingresado es negativo o mayor que el total, sale de bluque       
        if (numDep>0 and numDep>n):                 
            break                                    
        elif (cantAntenas>=0 and areaDep>=0):
            calcular_antenas(areaDep,cantAntenas,TipoAntena)    #Se llama a la funcion, retorna 'resultado'
                    
                    #Formato de la lista resultado=[NumeroAntenas,TipoA,TipoB,TipoC,TipoD,TipoE]
            
            lista_departamento[numDep-1]=[resultado[0]+lista_departamento[numDep-1][0],     #Numero de antenas
                                          resultado[1]+lista_departamento[numDep-1][1],     #Tipo A
                                          resultado[2]+lista_departamento[numDep-1][2],     #Tipo B
                                          resultado[3]+lista_departamento[numDep-1][3],     #Tipo C
                                          resultado[4]+lista_departamento[numDep-1][4],     #Tipo D
                                          resultado[5]+lista_departamento[numDep-1][5]]     #Tipo E
            break
    #Formato de la lista de departamentos
    #lista=[[NumeroAntenas,TipoA,TipoB,TipoC,TipoD,TipoE],[NumeroAntenas,TipoA,TipoB,TipoC,TipoD,TipoE], ...]
    #      [[            Departamento 1                 ],[                  Departamento 2           ], ...]
            
#-------------------------------------  SALIDAS  ------------------------------------------

#Ciclo para imprimir por cada departamento la cantidad de nuevas antenas y el tipo con menor y mayor cantidad
for z in range(n):
    print(z+1)                              #Se imprime el departamento
    print(lista_departamento[z][0])         #Se imprime la cantidad de nuevas antenas
    buscar_tipo_antenas(z)                  #Se llama la funcion para buscar el tipo de antenas de mas y menos cantidad
    
    # Retorna de la funcion 'tipo_antena'
    # Formato de la lista tipo_antena=[tipomenor,valormenor,tipomayor,valormayor]  
    
    #Se establece el tipo de antenas menor segun el resultado retornado
    
    if antenas.get(tipo_antena[0]):                       
        print(antenas[tipo_antena[0]],tipo_antena[1])     #Se imprime el tipo de antena con su valor
    
    #Se establece el tipo de antenas mayor segun el resultado retornado
    if antenas.get(tipo_antena[2]):
        print(antenas[tipo_antena[2]],tipo_antena[3])      #Se imprime el tipo de antena con su valor
    
#Ciclo para imprimir cada tipo de antena con el departamento de menor y mayor cantidad    
for z in range(5):      
    buscar_departamento(z+1)       #Funcion: Se busca por departamento cada tipo de antena mayor y menor
    
    if antenas.get(z):                          #Se obtiene el tipo de antena 
        print(res_buscar_dep[0],antenas[z],res_buscar_dep[1])   #Se imprime el departamento, tipo y valor para el menor
    
    if antenas.get(z):
        print(res_buscar_dep[2],antenas[z],res_buscar_dep[3])   #Se imprime el departamento, tipo y valor para el mayor




# print("Lista de prueba",lista_departamento)   
#lista=[[NumeroAntenas,TipoA,TipoB,TipoC,TipoD,TipoE],[NumeroAntenas,TipoA,TipoB,TipoC,TipoD,TipoE],...]