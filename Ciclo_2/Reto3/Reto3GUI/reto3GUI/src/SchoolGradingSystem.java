import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SchoolGradingSystem extends GradingSystem {

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtCantNotas;

    @FXML
    private TextArea txtDatos;

    @FXML
    private TextArea txtResultado;

    @FXML
    private Label lblNotas;

    @FXML
    private Label lblDatos;

    @FXML
    private Label lblResultado;

    @FXML
    private Label lblAdvertencia;

    @FXML
    public void btnCalcularClic(ActionEvent event) {
           
        loadData();
        
        txtResultado.setText(Double.toString(stat1())+"\n"
                            +Double.toString(stat2())+"\n"
                            +stat3()+"\n"
                            +stat4()); 
        
    }

    public void loadData(){
        
        int pos;
        cantNotas=Integer.parseInt(txtCantNotas.getText());
        // Scanner leer=new Scanner(System.in);
        // cantNotas=Integer.parseInt(leer.nextLine());
        String[] dato=txtDatos.getText().split("\n");
        String[] dato1={};
        
        if (cantNotas>dato.length){
            lblAdvertencia.setText("");
            JOptionPane.showMessageDialog(null, "La cantidad de notas ingresada no esta correcta. Faltan "
                                            +(cantNotas-dato.length)+" registros en el area de datos");
            
        }else{
            lblAdvertencia.setText("");
        
            for (int i=0;i<cantNotas;i++){
                dato1=dato[i].split(" ");
                pos=(int)(Double.parseDouble(dato1[0]));
                
                switch(dato1[2]){
                    
                    case "1.0":     //Caso Idiomas
                    matriz[pos-1][2]=dato1[3];
                    break;
                    
                    case "2.0":     //Caso Historia
                    matriz[pos-1][3]=dato1[3];
                    break;
                    
                    case "3.0":     //Caso Literatura
                    matriz[pos-1][4]=dato1[3];
                    break;
                    
                    default:
                    break;
                }

                
            }
        }
        if(cantNotas<dato.length){
            lblAdvertencia.setText("Advertencia, la cantidad de notas es menor a los datos ingresados. \nSobran "
            +(dato.length-cantNotas)+" registros en el area de datos");
        }

    }           
    // leer.close();
}
