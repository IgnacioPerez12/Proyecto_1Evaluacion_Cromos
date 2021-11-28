package controlador;

import dominio.Usuario;
import java.util.*;
import negocio.*;

public class Test {

    public static void main(String[] args) {
        
        IntNegocio metodosWeb = new NegocioImpl();
        Scanner entrada = new Scanner(System.in);
        int entradaSwitch;
        do {
            System.out.println("    SELECCIONA UNA OPCION - COLECCION FUTBOLISTAS\n");
            System.out.println("        1. Iniciar Sesion");
            System.out.println("        2. Registrarse");
            System.out.println("        0. Salir");
        
            entradaSwitch = entrada.nextInt();
            switch(entradaSwitch){

               case 1:

                    System.out.println("\nIntroduce un email");
                    entrada.nextLine();
                    String correo = entrada.nextLine();

                    System.out.println("\nIntroduce una password");
                    String contraseña = entrada.nextLine();

                    metodosWeb.iniciarSesion(correo, contraseña);

                  break;

               case 2:
                   System.out.println("\nIntroduce un email: ");
                   entrada.nextLine();
                   correo = entrada.nextLine();
                   System.out.println("\nIntroduce una contraseña: ");
                   contraseña = entrada.nextLine();

                   Usuario usu = new Usuario(correo, contraseña, 0, false);
                   if( correo == "" && contraseña == ""){
                       System.out.println("Error, rellene todos los campos");
                   } else {
                       metodosWeb.crearCuenta(usu);
                   }
                   break;


               case 0:
                   //SALIR
                   System.out.println("Hasta la próxima");
                   break;

               default : 
                   System.out.println("Seleccione una opcion valida");
            }
        } while (entradaSwitch > 0);

    }
    
}
