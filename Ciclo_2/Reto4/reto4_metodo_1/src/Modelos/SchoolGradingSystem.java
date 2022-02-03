package Modelos;


public class SchoolGradingSystem extends GradingSystem{


    public void loadData(String datos){
     
        String[] dato1=datos.split("\n");
        this.matriz=new String [dato1.length][4];
        
        for(int i=0;i<dato1.length;i++){
            String [] dato2 = dato1[i].split(" ");
            
            for(int j=0;j<dato2.length;j++){
                matriz[i][j] = dato2[j];
            }
        }
            
    }

    public String[] castControl(double nombre, double materia){
        String [] listNombre={"armando","nicolas","daniel","maria","marcela","alexandra"};
        String [] listMaterias={"idiomas","historia","literatura"};
        
        String[] respuesta=new String[2];

        switch(String.valueOf(nombre)){
            case "1.0":
                respuesta[0]=listNombre[0];
                break;
            case "2.0":
                respuesta[0]=listNombre[1];
                break;
            case "3.0":
                respuesta[0]=listNombre[2];
                break;
            case "4.0":
                respuesta[0]=listNombre[3];
                break;
            case "5.0":
                respuesta[0]=listNombre[4];
                break;
            case "6.0":
                respuesta[0]=listNombre[5];
                break;
            default:
                respuesta[0]="false";
                break;
        }



        switch (String.valueOf(materia)) {
            case "1.0":
                respuesta[1]=listMaterias[0];
                break;
            case "2.0":
            respuesta[1]=listMaterias[1];
                break;
            case "3.0":
            respuesta[1]=listMaterias[2];
                break;
        
            default:
            respuesta[1]="false";
                break;
        }
        
        return respuesta;
    }
}
