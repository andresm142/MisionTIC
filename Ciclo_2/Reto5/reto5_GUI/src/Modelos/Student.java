package Modelos;

import java.util.HashMap;

public class Student {
    private float nombre;
    private float genero;
    private HashMap<Float,Float> notas = new HashMap<Float,Float>();
   
    public float getNombre() {
        return nombre;
    }

    public void setNombre(float nombre) {
        this.nombre = nombre;
    }

    public float getGenero() {
        return genero;
    }

    public void setGenero(float genero) {
        this.genero = genero;
    }

    public HashMap<Float, Float> getNotas() {
        return notas;
    }

    public void setNotas(HashMap<Float, Float> notas) {
        this.notas = notas;
    }

    public Student(float nombre, float genero, String listaNotas) {
        this.nombre = nombre;
        this.genero = genero;
        
        String [] notasDivididas= listaNotas.split("\n");
        for(int i=0; i<notasDivididas.length;i++){
            String [] datos = notasDivididas[i].split(" ");
            this.notas.put(Float.parseFloat(datos[0]), Float.parseFloat(datos[1]));

        }
    }

    public Student() {
    }
    
    
}
