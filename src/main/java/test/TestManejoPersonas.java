package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {
//Bajo acoplamiento y alta coesion 
        PersonaDAO personaDao = new PersonaDAO();
        List<Persona> personas = personaDao.seleccionar();
        /*FOR EACH
        for (Persona persona : personas) {
            System.out.println("Usuario: " + persona);
        }
         */
        //LAMDA
        personas.forEach(persona
                -> {
            System.out.println("Usuario: " + persona);
        });
    }
}
