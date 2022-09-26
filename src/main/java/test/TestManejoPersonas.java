package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {
//Bajo acoplamiento y alta coesion 
        PersonaDAO personaDao = new PersonaDAO();

        //INSERTANDO UN NUEVO OBJETO DE TIPO PERSONA
         Persona usuarioNuevo = new Persona("Lionel", "Messi", "LioMessi@gmail.com", "5491123092978");
         personaDao.insertar(usuarioNuevo);
        
        
        //MODIFICANDO UN OBJETO DE PERSONA EXISTENTE
        Persona usuarioModificado = new Persona(8, "Pedro", "Aznar", "pedritoAznar@gmail.com", "1123462473");
        personaDao.actualizar(usuarioModificado);
        
        //ELIMINANDO UN OBJETO DE PERSONA EXISTENTE
        Persona usuarioEliminado = new Persona(11);
        personaDao.eliminar(usuarioEliminado);
        
        //LISTADO DE PERSONAS
        List<Persona> personas = personaDao.seleccionar();

        //LAMDA
        personas.forEach(persona
                -> {
            System.out.println("Persona: " + persona);
        });

    }
}
