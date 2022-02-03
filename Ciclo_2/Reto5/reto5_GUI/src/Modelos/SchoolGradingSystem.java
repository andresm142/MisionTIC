package Modelos;
import java.util.Map.Entry;

public class SchoolGradingSystem extends GradingSystem{
 
    public void loadData(int cantNotas,String registro){
        
        float nombre = 0;
        float genero = 0;
        String datos = "";
        boolean tempNom=false;
        boolean encontrado=false;

        this.cantNotas=cantNotas;

        String [] datosDivididos=registro.split("\n");
        
        for(int i=0;i<cantNotas;i++){
                        
            String [] registros=datosDivididos[i].split(" ");
            
            if(estudiantes.size()==0){
                tempNom=true;
                nombre = Float.parseFloat(registros[0]);
                genero = Float.parseFloat(registros[1]);
                datos = registros[2] + " " + registros[3] +"\n";
                estudiantes.add(new Student(nombre,genero,datos));
            }

            if(!tempNom){
                int size=estudiantes.size();
                for(int j=0;j<size;j++){
                    datos="";
                    if(estudiantes.get(j).getNombre()==Double.parseDouble(registros[0])){
                        
                        encontrado=true;
                        nombre = estudiantes.get(j).getNombre();
                        genero = estudiantes.get(j).getGenero();
                        
                        for ( Entry<Float, Float> e : estudiantes.get(j).getNotas().entrySet() ) {
                            float key = e.getKey();
                            Float val = e.getValue();
                            datos=datos + String.valueOf(key)+" "+String.valueOf(val) +"\n";
                        }
                        datos=datos + registros[2] + " " + registros[3] +"\n";
                        estudiantes.set(j, new Student(nombre,genero,datos));
                    }
                }

                if(!encontrado){
                    nombre = Float.parseFloat(registros[0]);
                    genero = Float.parseFloat(registros[1]);
                    datos = registros[2] + " " + registros[3] +"\n";
                    estudiantes.add(new Student(nombre,genero,datos));
                }
                
            }
            encontrado=false;
            tempNom=false;
        }
         
    }
}
