
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


/*

Map<String, String> map = new HashMap<String, String>();

map.put("offset","3");
map.put("items","50");
map.put("sort","title,asc");

for (Map.Entry<String, String> entry : map.entrySet()) {
    System.out.println(
        String.format("llave: %s, valor: %s", entry.getKey(), entry.getValue())
    );
}

https://es.stackoverflow.com/questions/121575/hashmap-obtener-si-existe-la-clave-a-partir-de-posibles-candidatos-en-java
*/