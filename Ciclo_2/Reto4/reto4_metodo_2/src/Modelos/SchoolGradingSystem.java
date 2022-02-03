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
}
