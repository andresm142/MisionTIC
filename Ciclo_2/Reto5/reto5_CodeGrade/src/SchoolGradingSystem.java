
import java.util.Scanner;
import java.util.Map.Entry;


public class SchoolGradingSystem extends GradingSystem{
    

    public void loadData(){
        Scanner leer =  new Scanner(System.in);

        cantNotas =  Integer.parseInt(leer.nextLine());
        float nombre = 0;
        float genero = 0;
        String datos = "";
        boolean tempNom=false;
        boolean encontrado=false;
        
        for(int i=0;i<cantNotas;i++){
            
            
            String [] registros=leer.nextLine().split(" ");

            
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

        // Scanner leer=new Scanner(System.in);
        // float nombre = 0;
        // float genero = 0;
        // String datos = "";

        // cantNotas=Integer.parseInt(leer.nextLine());

        
        // for(int i=0;i<cantNotas;i++){
            
            
        //     String [] registros=leer.nextLine().split(" ");
            
        //     if (nombre != Float.parseFloat(registros[0])){
        //         if(nombre != 0){
        //             estudiantes.add(new Student(nombre,genero,datos));
        //         }
        //         nombre = Float.parseFloat(registros[0]);
        //         genero = Float.parseFloat(registros[1]);
        //         datos = registros[2] + " " + registros[3] +"\n";
        //     }else{
        //         datos += registros[2] + " " + registros[3] +"\n";
        //     }

        //     if(i==cantNotas-1){
        //         estudiantes.add(new Student(nombre,genero,datos));
        //     }

        // }
        leer.close();
    }
}
