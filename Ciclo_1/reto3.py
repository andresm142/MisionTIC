from math import ceil
a=35600
b=6800
c=59300
d=24200
e=7400
n=0
lista_departamento=[]
dep_menor=0
dep_mayor=0

def calcular_antenas(area,cantidad,tipo):               #Funcion para calcular nuemero de antenas y tipos
    global valor
    valor=[0,0]
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
        
    valor=[NumeroAntenas,TipoA]
    return valor                    # Se retorna el numero de antenas y la cantidad segun tipo


while True:                     #Leer hasta que sean datos validos
    n,m=input().split(" ")   #Cantidad de departamentos y terrenos
    n=int(n)
    m=int(m)
   
    if n>=1:                    # Departamentos mayor o igual a 1
        break
    
for x in range(1,n+1):                      # Se crea la lista con el total de departamentos
    lista_departamento.append(x)
    lista_departamento[x-1]=[0+x,0,0]

    
for i in range(m):                      # Lectura de los datos segun la cantidad de terrenos
    while True:
        numDep,areaDep,cantAntenas,TipoAntena=input().split(" ")     #Se leen departamento, area,antenas y tipo
        numDep,areaDep,cantAntenas=int(numDep),eval(areaDep),int(cantAntenas)
        if (numDep>0 and numDep>n):                 # Si el departamento ingresado es negativo o mayor que el total,
            break                                   # sale de bluque
        elif (cantAntenas>=0 and areaDep>=0):
            calcular_antenas(areaDep,cantAntenas,TipoAntena)        #Se llama a la funcion
            
            temp1=valor[0]          # Numero de antenas
            temp2=valor[1]          #TipoA
            temp3=lista_departamento[numDep-1][1]           #Valor anterior numero antenas
            temp4=lista_departamento[numDep-1][2]           #Valor anterior tipo antena
            
            lista_departamento[numDep-1]=[numDep,temp1+temp3,temp2+temp4]    
            #lista=[[departamento 1, NumeroAntenas, TipoA],[departamento 2, NumeroAntenas, TipoA],...]
            break
        


# Se escoge el primer departamento y su cantidad de antenas
mayor=lista_departamento[0][1]
menor=lista_departamento[0][1]
dep_mayor=lista_departamento[0][0]
dep_menor=lista_departamento[0][0]
    
# Se busca el departamento con menor y mayor numeros de antenas
for j in range(n):
    if (menor > lista_departamento[j][1]):
        menor=lista_departamento[j][1]          #Se reemplaza la variable con el nuevo valor menor
        dep_menor= lista_departamento[j][0]     #Se asigna el numero del departamento donde se encuentra
        
    if (mayor < lista_departamento[j][1]):
        mayor=lista_departamento[j][1]          #Se reemplaza la variable con el nuevo valor meyor
        dep_mayor= lista_departamento[j][0]     #Se asigna el numero del departamento donde se encuentra
         

#SALIDAS
  
print(dep_menor,menor)              # Departamento con menor numero de antenas
print(dep_mayor,mayor)              # Departamento con mayor numero de antenas
for z in range(n):
    if lista_departamento[z][1]==0:     #Se busca que departamento no tiene antenas y se reemplaza por 1
        lista_departamento[z][1]=1      #Esto para evitar divicion por cero
    #Se imprime la lista de departamentos con el porcentaje de antenas Tipo a    
    print(lista_departamento[z][0],"{:.2f}".format((lista_departamento[z][2]*100)/lista_departamento[z][1])+"%")

    
