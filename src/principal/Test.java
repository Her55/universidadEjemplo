
package principal;

import entidades.Alumno;
import persistencia.AlumnoData;
import persistencia.Conexion;

/**
 *
 * @author Hernan
 */
public class Test {
   private Conexion conexion;
    private AlumnoData alumnoData; 
    
    public static void main(String[] args) {
        Alumno alu=new Alumno();
        //Alumno alu= new Alumno(30893000, "Viltez", "Hernan", "2023-06-15", true);
        new Test().conectar(alu);      
        
    }
    void conectar(Alumno alumno){
        //creo la conexion a la bd
        conexion = new Conexion("jdbc:mariadb://localhost:3306/universidadulp", "root", "");
        alumnoData = new AlumnoData(conexion);
        //alumnoData.guardarAlumno(alumno);
        
        
        alumno=alumnoData.buscarAlumno(1);
        if (alumno.getId()==0) {
            System.out.println("Alumno no existe");
        }else{
            System.out.println(alumno.toString());
        }
        
    }
}
