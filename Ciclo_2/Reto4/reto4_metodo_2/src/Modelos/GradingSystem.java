package Modelos;


public class GradingSystem {
    protected String[][] matriz;            // Matriz= {nombre, genero, materia, nota}
    protected String mejorEstIdiomas="";
    protected String genero="";
    protected double porcentaje,varianza,media=0,total_notas=0;
    protected int contSobresaliente=0;
    
        
    //****************** stat1 ********************** Varianza ****************

    public double stat1(){                                                      
        double sumaNotas=0,tempSobre=0;
        
        for(int x=0;x<matriz.length;x++){
            sumaNotas=sumaNotas+Double.parseDouble(matriz[x][3]);   //Suma total de notas
            tempSobre=Double.parseDouble(matriz[x][3]);   // Contador de soresalientes
                if(tempSobre>8 && tempSobre<=9){
                    contSobresaliente++;
                }
            
        }
        
        // Se calcula la varianza
        media=sumaNotas/matriz.length;
        
        for(int i = 0 ; i < matriz.length; i++){
            double rango;
            rango = Math.pow(Double.parseDouble(matriz[i][3]) - media, 2f);
            varianza=varianza+rango; 
        }
        varianza=varianza/matriz.length;
      
        varianza=Math.round(varianza*100)/100.0;
        return varianza;
    }

    //****************** stat2 ********************** Porcentaje ****************

    public double stat2(){                                                       
        // DecimalFormat decimales = new DecimalFormat("0.00");
        double temp=((float)contSobresaliente)/((float)matriz.length);
        // String porcentaje=decimales.format(temp);
        porcentaje=Math.round(temp*100)/100.0;
        return porcentaje;
    }

    //****************** stat3 ********************** Genero ****************

    public String stat3(){                                                       
        // Se calcula el genero con mejor promedio
         
        int contM=0,contF=0;
        float notasF=0,notasM=0;
        for(int x=0;x<matriz.length;x++){

            if (matriz[x][1].equals("m")){
                notasM=notasM+Float.parseFloat(matriz[x][3]);
                contM++;
            } else if(matriz[x][1].equals("f")){
                notasF=notasF+Float.parseFloat(matriz[x][3]);
                contF++;
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
        
        float mayor=0;
        for(int x=0;x<matriz.length;x++){
            if(matriz[x][2].equals("idiomas")){
                if(mayor<Double.parseDouble(matriz[x][3])){
                    mayor=Float.parseFloat(matriz[x][3]);
                    mejorEstIdiomas=matriz[x][0];
                }
            }
        }
        return mejorEstIdiomas;
    }
}
