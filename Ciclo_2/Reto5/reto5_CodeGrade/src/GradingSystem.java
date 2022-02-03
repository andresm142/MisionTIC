import java.util.ArrayList;

public class GradingSystem {
    protected String[][] matriz;            // Matriz= {nombre, genero, materia, nota}
    protected String mejorEstIdiomas="";
    protected String genero="";
    protected double porcentaje,varianza,media=0,total_notas=0;
    protected int cantNotas,contSobresaliente=0;
    protected ArrayList<Student> estudiantes = new ArrayList<Student>();
    Student estudiante1 = new Student();
         
    //****************** stat1 ********************** Varianza ****************

    public GradingSystem() {
    }

    public double stat1(){   
        double sumaNotas=0,tempSobre=0;
        
        for(int i=0; i<estudiantes.size();i++){

            for(float notas: estudiantes.get(i).getNotas().keySet()){
               sumaNotas += estudiantes.get(i).getNotas().get(notas);   //Suma total de notas
               tempSobre=estudiantes.get(i).getNotas().get(notas);   // Contador de soresalientes
                if(tempSobre>8 && tempSobre<=9){
                    contSobresaliente++;
                }
            }
        }


        // Se calcula la varianza

        media=sumaNotas/cantNotas;
        
        for(int i=0; i<estudiantes.size();i++){

            for(float notas: estudiantes.get(i).getNotas().keySet()){
                double rango;
                rango = Math.pow(estudiantes.get(i).getNotas().get(notas) -media, 2f);
                varianza=varianza+rango; 
            }
        }
  
        
        varianza=varianza/cantNotas;
      
        varianza=Math.round(varianza*100)/100.0;
        return varianza;
    }

    //****************** stat2 ********************** Porcentaje ****************

    public double stat2(){                                                       
        
        double temp=((float)contSobresaliente)/((float)cantNotas);
        
        porcentaje=Math.round(temp*100)/100.0;
        return porcentaje;
    }

    //****************** stat3 ********************** Genero ****************

    public String stat3(){                                                       
        // Se calcula el genero con mejor promedio
         
        int contM=0,contF=0;
        float notasF=0,notasM=0;

        for(int i=0; i<estudiantes.size();i++){
           

            if(estudiantes.get(i).getGenero()==0.0){
                for(float notas: estudiantes.get(i).getNotas().keySet()){
                    notasM += estudiantes.get(i).getNotas().get(notas);   //Suma total de notas
                    contM++;
                    
                 }
            }if(estudiantes.get(i).getGenero()==1.0){
                for(float notas: estudiantes.get(i).getNotas().keySet()){
                    notasF += estudiantes.get(i).getNotas().get(notas);   //Suma total de notas
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
        
        float mayor=0;
        
        for(int i=0; i<estudiantes.size();i++){
 
            if(mayor<estudiantes.get(i).getNotas().get(1.0f)){
                mayor=estudiantes.get(i).getNotas().get(1.0f);
                mejorEstIdiomas=String.valueOf(estudiantes.get(i).getNombre());
            }
         
        }


        switch(mejorEstIdiomas){
            case "1.0":
            mejorEstIdiomas="armando";
            break;
            case "2.0":
            mejorEstIdiomas="nicolas";
            break;
            case "3.0":
            mejorEstIdiomas="daniel";
            break;
            case "4.0":
            mejorEstIdiomas="maria";
            break;
            case "5.0":
            mejorEstIdiomas="marcela";
            break;
            case "6.0":
            mejorEstIdiomas="alexandra";
            break;
            default:
            break;
        }
        return mejorEstIdiomas;
    }
}
