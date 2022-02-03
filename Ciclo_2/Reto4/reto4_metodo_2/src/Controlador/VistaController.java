package Controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javafx.scene.input.KeyEvent;
import Modelos.SchoolGradingSystem;
import Modelos.SqlController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class VistaController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombreIngresar;

    @FXML
    private TextField txtNotas;

    @FXML
    private TextField txtMateriaIngresar;

    @FXML
    private TextField txtGenero;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCargar;

    @FXML
    private TextArea txtNotasCargar;

    @FXML
    private Button btnProcesar;

    @FXML
    private TextArea txtResultado;

    @FXML
    private TextField txtNombreConsulta;

    @FXML
    private TextField txtMateriaConsulta;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TextArea txtResultadoConsulta;

    @FXML
    void cargarClick(ActionEvent event) throws SQLException {
        txtNotasCargar.clear();
        SqlController sqlController=new SqlController();
        txtNotasCargar.clear();
        String resultado=sqlController.leerDatos();
        if(resultado.equals("")){
            JOptionPane.showMessageDialog(null,"No hay registros");
        }else{
            txtNotasCargar.setText(resultado);
        }
        

    }
                    
    @FXML
    void consultarClick(ActionEvent event) {
        txtResultadoConsulta.clear();
        SqlController sqlController=new SqlController();
        String nombre=txtNombreConsulta.getText();
        String materia=txtMateriaConsulta.getText();
        String resultado=sqlController.consultarDatos(nombre, materia);
        if(resultado.equals("")){
            JOptionPane.showMessageDialog(null,"No hay registros");
            txtResultadoConsulta.setText("No hay registros");
        }else{
            txtResultadoConsulta.setText(resultado);
        }
    }

    @FXML
    void eliminarClick(ActionEvent event) {
        txtResultadoConsulta.clear();
        SqlController sqlController=new SqlController();
        String nombre=txtNombreConsulta.getText();
        String materia=txtMateriaConsulta.getText();
        sqlController.eliminarDatos(nombre, materia);

    }

    @FXML
    void guardarClick(ActionEvent event) throws SQLException {
        SqlController sqlController=new SqlController();
        String nombre=txtNombreIngresar.getText().replaceAll("\\s", "");
        String genero=txtGenero.getText().replaceAll("\\s", "");
        String materia=txtMateriaIngresar.getText().replaceAll("\\s", "");
        String nota=txtNotas.getText().replaceAll("\\s", "");
        if(nombre.equals("") || genero.equals("") || materia.equals("") || nota.equals("")){
            JOptionPane.showMessageDialog(null,"Llene todos los campos");
            return;
        }
        if(validarDatos(nombre, materia)==true){
            JOptionPane.showMessageDialog(null,"El nombre o la materia es incorrecto");
            return;
        }

        double notas=Double.parseDouble(txtNotas.getText());
        if(notas <0 || notas>10){
            JOptionPane.showMessageDialog(null,"La nota no puede ser menor a 0 y mayor a 10");
            
        }else{
            sqlController.insertarDatos(nombre, materia, genero, nota);
            txtNombreIngresar.clear();
            txtGenero.clear();
            txtMateriaIngresar.clear();
            txtNotas.clear();
        }
       
        
    }

    @FXML
    void procesarClick(ActionEvent event) {
        SchoolGradingSystem estuiante=new  SchoolGradingSystem();
        String datos= txtNotasCargar.getText();
        if(datos.equals("")){
            JOptionPane.showMessageDialog(null,"No hay datos para procesar");
        }else{
            estuiante.loadData(datos);
            
            txtResultado.setText(String.valueOf(estuiante.stat1()+"\n"+
                                                estuiante.stat2()+"\n"+
                                                estuiante.stat3()+"\n"+
                                                estuiante.stat4()+"\n"));
        }
    }

    @FXML
    void keyTypedGenero(KeyEvent event ) {

        if (txtGenero.getText().length() >= 1 ){ // limite a 1 caracter
                txtGenero.setText(txtGenero.getText().substring(0,1));
                txtGenero.end();                //Cursor al final del texto
                
        }

        if (!txtGenero.getText().equals("")){
            if (!txtGenero.getText().matches("[f m]") ) {
                txtGenero.clear();
            }

            
        }
    }

    @FXML
    void keyTypedNota(KeyEvent event) {
        if(!txtNotas.getText().equals("")){
            try{
                Double.parseDouble(txtNotas.getText());       //Comprueba que el textFiel solo tenga numeros, sino genera error
            
            }catch(Exception e)
            {
                txtNotas.clear();
                JOptionPane.showMessageDialog(null,"Error, ingrese solo numeros en el campo de notas y uso de punto (.)");
            }
        }

        if (txtNotas.getText().length()>=5){            // limite a 5 caracteres
            txtNotas.setText(txtNotas.getText().substring(0,4));
            txtNotas.end();         
        }
       
    }

    @FXML
    void initialize() {
        // txtNotasCargar.setEditable(false);
    }
    public boolean validarDatos(String nombre, String materia){
        String[] listNombres={"armando","nicolas","daniel","maria","marcela","alexandra"};
        String [] listMaterias={"idiomas","historia","literatura"};
        boolean error1=true;
        boolean error2=true;
        
        for(String nmb:listNombres){
            if(nombre.equals(nmb)){
                error1=false;
            }
        }

        for(String mat:listMaterias){
            if(materia.equals(mat)){
                error2=false;
            }
        }
        if(error1==true || error2==true){
            return true;
        }else{
            return false;
        }
        
    }
}
