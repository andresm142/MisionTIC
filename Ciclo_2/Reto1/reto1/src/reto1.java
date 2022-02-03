import java.text.DecimalFormat;
import java.util.Scanner;

public class reto1 {
   
    
    public static void main(String args[]) throws Exception {
        float idiomas=0,historia=0,literatura=0,mayor=0;
        double porcentaje=0,total_notas=0,media=0,varianza=0;
        String mejorEstIdiomas="";
        String[][] matriz={
            //Nombre,genero,idiomas,historia,literatura
            {"armando","m","0","0","0"},
            {"nicolas","m","0","0","0"},
            {"daniel ","m","0","0","0"},
            {"maria  ","f","0","0","0"},
            {"marcela","f","0","0","0"},
            {"alexandra","f","0","0","0"},
            };
        int pos=0,cont=0,num=0;

        DecimalFormat decimales = new DecimalFormat("0.00");        // 

        Scanner leer=new Scanner(System.in);
        // Leer numeros de registros
        num=leer.nextInt();
        leer.nextLine();        //Reiniciar buffer
        String[] dato={};
        double [] valor=new double[num];
        for (int i=0;i<num;i++){
            
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
            } // Fin del switch
            
            float temp=Float.parseFloat(dato[3]);   // Contador de soresalientes
            if(temp>8 && temp<=9){
                cont++;
            }
  
        }
        leer.close();
        
        // Se calcula el genero con mejor promedio
        float notasM=0,notasF=0;
        int contM=0,contF=0;
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
        media=total_notas/num;
        for(int i = 0 ; i < num; i++){
            double rango;
            rango = Math.pow(valor[i] - media, 2f);
            varianza = varianza + rango;
        }

        //----------------------- SALIDAS-------------------------------------

        System.out.println(decimales.format(varianza/num));         // Se imprime la varianza
        
        porcentaje=((float)cont)/((float)num);
        
        System.out.println(decimales.format(porcentaje));           // Se imprime el porcentaje de sobresaliente
        
        // Se imprime el genero con mejor promedio
        double m=Math.round((notasM/contM)*1000)/1000.0;
        double f=Math.round((notasF/contF)*1000)/1000.0;
       
        if(m>f){                      
            System.out.println("m");
        }else if(m==f){
            System.out.println("m");
        }else {
            System.out.println("f");
        }

        System.out.println(mejorEstIdiomas);                // Se imprime el estudiante con mejor desempeño en idiomas


    //     //INICIO Pruebas de matriz
    //     System.out.print("nombre|  \t");
    //     System.out.print("genero|\t");
    //     System.out.print("idiomas|");
    //     System.out.print("historia |");
    //     System.out.println("literatura");
    //     for (int x=0; x < matriz.length; x++) {
    //         System.out.print("|");
    //         for (int y=0; y < matriz[x].length; y++) {
    //           System.out.print (matriz[x][y]);
    //           if (y!=matriz[x].length-1) System.out.print("\t");
    //         }
    //         System.out.println("|");
    //     }
        // System.out.println("Promedio m:" +notasM/contM);
        // System.out.println("Promedio f:" +notasF/contF);
    //   //FIN PRUEBA MATRIZ
        
    }
}

