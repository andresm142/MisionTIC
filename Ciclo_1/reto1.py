from math import ceil
a=35600
b=6800
c=59300
d=24200
e=7400

AreaZona=float(input("Didige el area de la zona:"))
AntenasInstaladas=int(input("Digite la cantidadde antenas instaladas:"))
TipoAntena=str(input("Digite el tipo de antena a instalar:"))

AreaAntenasInstaladas=AntenasInstaladas*18400

if AreaZona>=0 and AntenasInstaladas>=0:
    if TipoAntena=="a":
        NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/a)
        if NumeroAntenas<0:
            NumeroAntenas=0
        print("Se necesitan",NumeroAntenas,"antenas")
    elif TipoAntena=="b":
        NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/b)
        if NumeroAntenas<0:
            NumeroAntenas=0
        print("Se necesitan",NumeroAntenas,"antenas")
    elif TipoAntena=="c":
        NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/c)
        if NumeroAntenas<0:
            NumeroAntenas=0
        print("Se necesitan",NumeroAntenas,"antenas")
    elif TipoAntena=="d":
        NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/d)
        if NumeroAntenas<0:
            NumeroAntenas=0
        print("Se necesitan",NumeroAntenas,"antenas")                    
    elif TipoAntena=="e":
        NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/e)
        if NumeroAntenas<0:
            NumeroAntenas=0
        print("Se necesitan",NumeroAntenas,"antenas")
    else:
        print("error en los datos")
else:   
    print("error en los datos")
                    



