import java.util.Vector;

public class GradingSystem {
    public String[][] matriz={{"armando","m","0","0","0"},    //Nombre,genero,idioma,historia,literatura
                                {"nicolas","m","0","0","0"},
                                {"daniel ","m","0","0","0"},
                                {"maria  ","f","0","0","0"},
                                {"marcela","f","0","0","0"},
                                {"alexandra","f","0","0","0"},
                                };
    public String mejorEstIdiomas="";
    public String genero="";
    public double porcentaje,varianza,media=0,total_notas=0;
    public int cantNotas,contSobresaliente=0;
       
    public GradingSystem(){
    }
    
    //****************** stat1 ********************** Varianza ****************

    public double stat1(){                                                      
        double sumaNotas=0,tempSobre=0;
        varianza=0;contSobresaliente=0;
        Vector<Double> valor=new Vector<Double>(cantNotas);    
        for(int x=0;x<matriz.length;x++){
             
            for (int y=2; y < matriz[x].length; y++) {
                sumaNotas=sumaNotas+Double.parseDouble(matriz[x][y]);   //Suma total de notas
                valor.add(Double.parseDouble(matriz[x][y]));
                
                tempSobre=Double.parseDouble(matriz[x][y]);   // Contador de soresalientes
                if(tempSobre>8 && tempSobre<=9){
                    contSobresaliente++;
                }
            }
        }
        
        // Se calcula la varianza
        media=sumaNotas/cantNotas;
        for(int i = 0 ; i < 18; i++){
            double rango;
            rango = Math.pow(valor.get(i) - media, 2f);
            varianza=varianza+rango; 
        }
        varianza=varianza/cantNotas;
        // DecimalFormat decimales = new DecimalFormat("0.00");
        // String temp=decimales.format(varianza);
        varianza=Math.round(varianza*100)/100.0;
        return varianza;
    }

    //****************** stat2 ********************** Porcentaje ****************

    public double stat2(){     
        porcentaje=0;                                                  
        // DecimalFormat decimales = new DecimalFormat("0.00");
        double temp=((float)contSobresaliente)/((float)cantNotas);
        // String porcentaje=decimales.format(temp);
        porcentaje=Math.round(temp*100)/100.0;
        return porcentaje;
    }

    //****************** stat3 ********************** Genero ****************

    public String stat3(){                                                       
             // Se calcula el genero con mejor promedio
        genero="";
        int contM=0,contF=0;
        float notasF=0,notasM=0;
        for(int x=0;x<matriz.length;x++){
             
            for (int y=2; y < matriz[x].length-1; y++) {
                if (matriz[x][1].equals("m")){
                    notasM=notasM+Float.parseFloat(matriz[x][y]);
                    contM++;
                } else if(matriz[x][1].equals("f")){
                    notasF=notasF+Float.parseFloat(matriz[x][y]);
                    contF++;
                }
                
            }
           
        }

        double m=Math.round((notasM/contM)*1000)/1000.0;
        double f=Math.round((notasF/contF)*1000)/1000.0;
        
        if(m>f){                      
            genero="m";
        }else if(m==f){
            genero="m";
        }else {
            genero="f";
        }
        
        return genero;
    }

    //****************** stat4 ********************** Mejor Estudiante idiomas ****************

    public String stat4(){                                                    
        mejorEstIdiomas="";
        float mayor=0;
        for(int x=0;x<matriz.length;x++){
            if(mayor<Double.parseDouble(matriz[x][2])){
                mayor=Float.parseFloat(matriz[x][2]);
                mejorEstIdiomas=matriz[x][0];
            }

        }
        return mejorEstIdiomas;
    }
}
