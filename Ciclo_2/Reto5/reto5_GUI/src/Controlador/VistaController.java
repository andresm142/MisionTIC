package Controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Modelos.SchoolGradingSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VistaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void btnCalcularClic(ActionEvent event) {
        SchoolGradingSystem obj=new SchoolGradingSystem();
        try{     
        obj.loadData(Integer.parseInt(txtCantNotas.getText()),txtDatos.getText());
        
        txtResultado.setText(Double.toString(obj.stat1())+"\n"
                            +Double.toString(obj.stat2())+"\n"
                            +obj.stat3()+"\n"
                            +obj.stat4()); 
        
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, "Error en los datos. Revise si todos los datos estan corrector");
        }
    }

    @FXML
    void initialize() {

    }
}
