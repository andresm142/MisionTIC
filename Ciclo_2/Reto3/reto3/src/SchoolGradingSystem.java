import java.util.Scanner;

public class SchoolGradingSystem extends GradingSystem{

      

    public SchoolGradingSystem() {
        // super();
       }

    public void loadData(){
        
        int pos;
        Scanner leer=new Scanner(System.in);
        cantNotas=Integer.parseInt(leer.nextLine());
        String[] dato={};
        
        for (int i=0;i<cantNotas;i++){
            dato=leer.nextLine().split(" ");
            pos=(int)(Double.parseDouble(dato[0]));
            
            switch(dato[2]){
                
                case "1.0":     //Caso Idiomas
                matriz[pos-1][2]=dato[3];
                break;
                
                case "2.0":     //Caso Historia
                matriz[pos-1][3]=dato[3];
                break;
                
                case "3.0":             //Caso Literatura
                matriz[pos-1][4]=dato[3];
                break;
                
                default:
                break;
            }

            
        }

                
    leer.close();}
    

}
