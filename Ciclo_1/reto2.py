from math import ceil
a=35600
b=6800
c=59300
d=24200
e=7400

ZonasAnalizar=int(input())

TotalAntenas=0
TipoA=0
TipoB=0
TipoC=0
TipoD=0
TipoE=0

if ZonasAnalizar>0:
    for i in range(ZonasAnalizar):
        AreaZona=float(input())
        AntenasInstaladas=int(input())
        TipoAntena=str(input()).lower()
        i+=1
        AreaAntenasInstaladas=AntenasInstaladas*18400
        if AreaZona>=0 and AntenasInstaladas>=0:
            if TipoAntena=="a":
                NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/a)
                if NumeroAntenas<0:
                    NumeroAntenas=0
                TipoA=TipoA+NumeroAntenas
            elif TipoAntena=="b":
                NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/b)
                if NumeroAntenas<0:
                    NumeroAntenas=0
                TipoB=TipoB+NumeroAntenas
            elif TipoAntena=="c":
                NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/c)
                if NumeroAntenas<0:
                    NumeroAntenas=0
                TipoC=TipoC+NumeroAntenas
            elif TipoAntena=="d":
                NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/d)
                if NumeroAntenas<0:
                    NumeroAntenas=0
                TipoD=TipoD+NumeroAntenas
            elif TipoAntena=="e":
                NumeroAntenas=ceil((AreaZona-AreaAntenasInstaladas)/e)
                if NumeroAntenas<0:
                    NumeroAntenas=0
                TipoE=TipoE+NumeroAntenas


TotalAntenas=TipoA+TipoB+TipoC+TipoD+TipoE
print(TotalAntenas)
if TotalAntenas==0:
    TotalAntenas=1
    
TipoA="{:.2f}".format((TipoA*100)/TotalAntenas)
TipoB="{:.2f}".format((TipoB*100)/TotalAntenas)
TipoC="{:.2f}".format((TipoC*100)/TotalAntenas)
TipoD="{:.2f}".format((TipoD*100)/TotalAntenas)
TipoE="{:.2f}".format((TipoE*100)/TotalAntenas)
print("a",TipoA+"%")
print("b",TipoB+"%")
print("c",TipoC+"%")
print("d",TipoD+"%")
print("e",TipoE+"%")