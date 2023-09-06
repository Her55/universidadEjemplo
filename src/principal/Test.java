
package principal;

import entidades.Alumno;
import entidades.Materia;
import persistencia.AlumnoData;
import persistencia.MateriaData;
import persistencia.Conexion;

/**
 *
 * @author Hernan
 */
public class Test {
    private Conexion conexion;
    private AlumnoData alumnoData;
    private MateriaData materiaData;
    
    public static void main(String[] args) {
       // Alumno alu=new Alumno();
        //Alumno alu= new Alumno(30893000, "Viltez", "Hernan", "2023-06-15", true);
        //new Test().conectar(alu); 
        Materia mat= new Materia("Matematica", 1, true);
        new Test().conectar(mat);         
        
    }
    void conectar(Alumno alumno){
        //creo la conexion a la bd
        conexion = new Conexion("jdbc:mariadb://localhost:3306/universidadulp", "root", "");
        alumnoData = new AlumnoData(conexion);
        
        //eliminar
        //alumnoData.EliminarPorDni(30893000);
        
        //guardar
        //alumnoData.guardarAlumno(alumno);
        
        //buscar alumno
        /*alumno=alumnoData.buscarAlumno(2);
        if (alumno.getId()==0) {
           System.out.println("Alumno no existe");
        }else{
            System.out.println(alumno.toString());
        }*/
        
    }
    
     void conectar(Materia materia){
        //creo la conexion a la bd
        conexion = new Conexion("jdbc:mariadb://localhost:3306/universidadulp", "root", "");
        materiaData = new MateriaData(conexion);
        materiaData.guardarMateria(materia);
     }
}
