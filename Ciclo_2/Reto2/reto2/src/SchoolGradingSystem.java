import java.text.DecimalFormat;
import java.util.Scanner;

public class SchoolGradingSystem {
    private String[][] matriz={{"armando","m","0","0","0"},
                                {"nicolas","m","0","0","0"},
                                {"daniel ","m","0","0","0"},
                                {"maria  ","f","0","0","0"},
                                {"marcela","f","0","0","0"},
                                {"alexandra","f","0","0","0"},
                                };
    private double total_notas=0,media=0,varianza=0;
    private String mejorEstIdiomas="";
    private int cont=0,cantNotas=0,contM=0,contF=0;
    private String[] dato={};
    private float notasM=0,notasF=0;

    public SchoolGradingSystem(){
        // readData();

    }

    public void readData(){
        float idiomas=0,historia=0,literatura=0,mayor=0;
        int pos=0;
        
        Scanner leer=new Scanner(System.in);
        cantNotas=leer.nextInt();
        leer.nextLine();        //Reiniciar buffer
        // String[] dato={};
        double [] valor=new double[cantNotas];
        for (int i=0;i<cantNotas;i++){
            
            dato=leer.nextLine().split(" ");
            pos=(int)(Double.parseDouble(dato[0]));
           
            // Se selecciona el caso segun la materia
            switch(dato[2]){
                case "1.0":     //Caso Idiomas
                
                idiomas=Float.parseFloat(matriz[pos-1][2]);
                idiomas=idiomas+Float.parseFloat(dato[3]);
                matriz[pos-1][2]=Float.toString(idiomas);
                total_notas=total_notas+Double.parseDouble(dato[3]);
                valor[i]=Double.parseDouble(dato[3]);
                if (mayor<Double.parseDouble(dato[3])){             //Establecer mejor estudiante idiomas
                    mayor=Float.parseFloat(dato[3]);
                    mejorEstIdiomas=matriz[pos-1][0];
                }
                break;
                case "2.0":     //Caso Historia
                
                historia=Float.parseFloat(matriz[pos-1][3]);
                historia=historia+Float.parseFloat(dato[3]);
                matriz[pos-1][3]=Float.toString(historia);
                total_notas=total_notas+Double.parseDouble(dato[3]);
                valor[i]=Double.parseDouble(dato[3]);
                
                break;
                case "3.0":             //Caso Literatura
               
                literatura=Float.parseFloat(matriz[pos-1][4]);
                literatura=literatura+Float.parseFloat(dato[3]);
                matriz[pos-1][4]=Float.toString(literatura);
                total_notas=total_notas+Double.parseDouble(dato[3]);
                valor[i]=Double.parseDouble(dato[3]);
                break;
                default:
                break;
            } 

            float temp=Float.parseFloat(dato[3]);   // Contador de soresalientes
            if(temp>8 && temp<=9){
                cont++;
            }
        }    
        leer.close();

         // Se calcula el genero con mejor promedio
         
        //  int contM=0,contF=0;
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
         
         // Se calcula la varianza
         media=total_notas/cantNotas;
         for(int i = 0 ; i < cantNotas; i++){
             double rango;
             rango = Math.pow(valor[i] - media, 2f);
             varianza = varianza + rango;
         }

    }

    public String question1(){
        DecimalFormat decimales = new DecimalFormat("0.00");
        String temp=decimales.format(varianza/cantNotas);
        return temp;
    }

    public String question2(){
        DecimalFormat decimales = new DecimalFormat("0.00");
        double porcentaje=((float)cont)/((float)cantNotas);
        String temp=decimales.format(porcentaje);
        return temp;
    }

    public String question3(){
        double m=Math.round((notasM/contM)*1000)/1000.0;
        double f=Math.round((notasF/contF)*1000)/1000.0;
        String temp="";
        if(m>f){                      
            // System.out.println("m");
            temp="m";
        }else if(m==f){
            // System.out.println("m");
             temp="m";
        }else {
            // System.out.println("f");
             temp="f";
        }
        // String temp="";
        return temp;
    }

    public String question4(){
        String temp=mejorEstIdiomas;
        return temp;
    }
}
