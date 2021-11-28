package datos;

import dominio.Estado_Perfil_Enum;
import dominio.Usuario;
import dominio.UsuarioPerfil;
import excepciones.*;
import java.io.*;
import java.util.*;

public class PerfilDatosImpl implements IntPerfilDatos{

    Scanner entrada = new Scanner(System.in);
        
    // Comprobar si existe
    @Override
    public boolean Existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);      
        return archivo.exists();
    }

    // Crear el archivo
    @Override
    public void Crear(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
            
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado con exito el archivo");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }           
        
    }

    // ¿ ADMIN ?
    
    // LISTAR ADMIN
    @Override
    public List<UsuarioPerfil> Listar(String nombreArchivo) throws LecturaEx {
        File archivo = new File(nombreArchivo);
        List<UsuarioPerfil> lista = new ArrayList<UsuarioPerfil>();
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            
            var lectura = entrada.readLine();
            while (lectura != null) {
                String[] cadenaPerfil = lectura.split(";");
                UsuarioPerfil newPerfil = new UsuarioPerfil(Integer.parseInt(cadenaPerfil[0]), cadenaPerfil[1], cadenaPerfil[2], cadenaPerfil[3], cadenaPerfil[4], cadenaPerfil[5], cadenaPerfil[6], cadenaPerfil[7]);
                lista.add(newPerfil);
                lectura = entrada.readLine();
            }
            entrada.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                throw new LecturaEx("Error al listar los perfiles");
            } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return lista;
    }

    // BUSCAR ADMIN
    @Override
    public String Buscar(String nombreArchivo, String buscar) throws LecturaEx {
        File archivo = new File(nombreArchivo);
        String mensaje = "";
        int linea = 0;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            
            while (lectura != null) {
                String[] cadenaPerfil = lectura.split(";");
                linea = linea + 1;

                if(cadenaPerfil[3].equalsIgnoreCase(buscar)){
                    mensaje = "Nombre del archivo : " + nombreArchivo + "\nLinea : " + linea + "\nPerfil: " + "\n ID: " + cadenaPerfil[0] + "\n Nombre: " + cadenaPerfil[1] + "\n Apellidos: " + cadenaPerfil[2] + "\n Correo: " + cadenaPerfil[3] + "\n Contraseña: " + cadenaPerfil[4] + "\n Ciudad: " + cadenaPerfil[5] + "\n Equipo favorito: " + cadenaPerfil[6] + "\n Estado de ánimo: " + cadenaPerfil[7];
                    break;
                }
                
                lectura = entrada.readLine();
            }
            
            if(lectura == null) {
                System.out.println("No existe un perfil con el correo que buscar");
            }
            
            entrada.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                System.out.println("Error al leerlo");
            } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }
        
        return mensaje;
    }

    // ¿ CLIENTE ?
    @Override
    public String findById(String nombreArchivo, int buscar) throws LecturaEx {
        File archivo = new File(nombreArchivo);
        String mensaje = "";
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            
            while (lectura != null) {
                String[] cadenaPerfil = lectura.split(";");

                if(Integer.parseInt(cadenaPerfil[0]) ==  buscar){
                    mensaje = "Perfil: " + "\n ID: " + cadenaPerfil[0] + "\n Nombre: " + cadenaPerfil[1] + "\n Apellidos: " + cadenaPerfil[2] + "\n Correo: " + cadenaPerfil[3] + "\n Contraseña: " + cadenaPerfil[4] + "\n Ciudad: " + cadenaPerfil[5] + "\n Equipo favorito: " + cadenaPerfil[6] + "\n Estado de ánimo: " + cadenaPerfil[7];
                    break;
                }
                
                lectura = entrada.readLine();
            }
            
            if(lectura == null) {
                System.out.println("No existe tu perfil");
            }
            
            entrada.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                System.out.println("Error al leerlo");
            } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }
        
        return mensaje;
    }

    @Override
    public void Escribir(UsuarioPerfil usuarioPerfil, String nombreArchivo, boolean anexar) throws EscrituraEx {
        File archivo = new File(nombreArchivo);
        String cadena = usuarioPerfil.getId_usuario() + ";" + usuarioPerfil.getNombre()+ ";" + usuarioPerfil.getApellidos() + ";" + usuarioPerfil.getCorreo() + ";" + usuarioPerfil.getPsw() + ";" + usuarioPerfil.getCiudad() + ";" + usuarioPerfil.getEquipo() + ";" + usuarioPerfil.getEstado();

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));

            salida.println(cadena);
            salida.close();
            System.out.println("Perfil añadido con exito\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            System.out.println("El archivo no existe");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void Borrar(String nombreArchivo, int buscar) {
        File archivo = new File(nombreArchivo);
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            
            //Creamos el archivo
            PrintWriter salida = new PrintWriter(archivo);
            System.out.println("Se ha creado con exito el archivo");
            
            while (lectura != null) {
                String[] cadenaPerfil = lectura.split(";");
                
                if(Integer.parseInt(cadenaPerfil[0]) == buscar){
                    lectura = entrada.readLine();
                    continue;
                }
                
                //Escribimos las lineas que no queremos eliminar
                salida = new PrintWriter(new FileWriter(archivo, true));
                salida.println(lectura);
                salida.close();
                
                //Pasamos a la siguiente linea
                lectura = entrada.readLine();
            }
            
            if(lectura == null) {
                if(archivo.exists() == true){
                    archivo.delete();
                    System.out.println("Perfil eliminado");
                } else {
                    System.out.println("El perfil que quieres eliminar no existe");
                }
            }
            
            entrada.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                System.out.println("Error al leerlo");
            } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }
    }

    //Modificar
    @Override
    public void Modificar(String nombreArchivo, String campo, int id_usuario) throws EscrituraEx {
        // 1. Busco al usuario
        
        UsuarioPerfil usu = null;
        File archivo = new File(nombreArchivo);
        int linea = 0;
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            
            while (lectura != null) {
                String[] cadenaPerfil = lectura.split(";");
                linea = linea + 1;

                // 2. Encapsulamos el usuario
                if(Integer.parseInt(cadenaPerfil[0]) == id_usuario){
                    usu = new UsuarioPerfil(Integer.parseInt(cadenaPerfil[0]), cadenaPerfil[1], cadenaPerfil[2], cadenaPerfil[3], cadenaPerfil[4], cadenaPerfil[5], cadenaPerfil[6], cadenaPerfil[7]);
                    System.out.println("\nPerfil usuario antiguo: " + usu);
                    break;
                }
                
                lectura = entrada.readLine();
            }
            
            if(lectura == null) {
                System.out.println("No existe un perfil con el correo que buscar");
            }
            
            entrada.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                System.out.println("Error al leerlo");
            } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }
        
        // 2.  Encapsular los datos
        switch(campo){
            case "nombre":
                System.out.println("Introduce el nuevo Nombre: ");
                String nombre = entrada.nextLine();
                usu.setNombre(nombre);
                
                break;
                
            case "apellidos":
                System.out.println("Introduce los nuevos Apellidos: ");
                String apellidos = entrada.nextLine();
                usu.setApellidos(apellidos);
                
                break;
                
            case "correo":
                System.out.println("Introduce el nuevo Correo: ");
                String correo = entrada.nextLine();
                usu.setCorreo(correo);
                
                //Falta el update a la tabla(sql) usuario
                break;
                
            case "contraseña":
                System.out.println("Introduce la nueva contraseña: ");
                String psw = entrada.nextLine();
                usu.setPsw(psw);
                
                break;
                
            case "ciudad":
                System.out.println("Introduce tu Ciudad: ");
                String ciudad = entrada.nextLine();
                usu.setCiudad(ciudad);
                
                break;
                
            case "equipo":
                System.out.println("Introduce el nuevo Equipo Favorito: ");
                String equipo = entrada.nextLine();
                usu.setEquipo(equipo);
                
                break;
                
            case "estado":
                System.out.println("Introduce tu estado anímico: ");
                System.out.println("    · Feliz");
                System.out.println("    · Triste");
                System.out.println("    · Tranquilo");
                System.out.println("    · Enfadado");
                System.out.println("    · Preocupado");
                System.out.println("    · Asustado");
                String estado = entrada.nextLine().toLowerCase();

                switch(estado){
                    case "feliz":
                        estado = Estado_Perfil_Enum.FELIZ.toString();
                        break;

                    case "triste":
                        estado = Estado_Perfil_Enum.TRISTE.toString();
                        break;

                    case "tranquilo":
                        estado = Estado_Perfil_Enum.TRANQUILO.toString();
                        break;

                    case "enfadado":
                        estado = Estado_Perfil_Enum.ENFADADO.toString();
                        break;

                    case "preocupado":
                        estado = Estado_Perfil_Enum.PREOCUPADO.toString();
                        break;

                    case "asustado":
                        estado = Estado_Perfil_Enum.ASUSTADO.toString();
                        break;
                }

                usu.setEstado(estado);
                
                break;
        }
        
        System.out.println("\nPerfil usuario nuevo: " + usu);
        
        // 4. Borrar el perfil antiguo
        Borrar(nombreArchivo, usu.getId_usuario());
        
        // 5. Escribir el nuevo perfil
        Escribir(usu, nombreArchivo, true);
    }
    
    
        /*
    
    1. Comprobar si existe el txt
    2. Si no existe lo CREA, si existe no lo creamos
    3. Listar el perfil del usuario;
        - Si no existe mostrar las opciones de ESCRIBIR(crear perfil); 
        - Si existe Mostar las opciones de MODIFICAR y ELIMINAR
  

        *Listar solo un usuario seria como un buscar por id
    */

    @Override
    public List <UsuarioPerfil> EncapsularById(String nombreArchivo, int buscar, String campo) throws LecturaEx {
        File archivo = new File(nombreArchivo);
        List<UsuarioPerfil> lista = new ArrayList<UsuarioPerfil>();
        UsuarioPerfil usuPerfil = null;
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            
            while (lectura != null) {
                String[] cadenaPerfil = lectura.split(";");

                if(Integer.parseInt(cadenaPerfil[0]) ==  buscar){
                    usuPerfil = new UsuarioPerfil(Integer.parseInt(cadenaPerfil[0]), cadenaPerfil[1], cadenaPerfil[2], cadenaPerfil[3], cadenaPerfil[4], cadenaPerfil[5], cadenaPerfil[6], cadenaPerfil[7]);
                    lista.add(usuPerfil);
                    break;
                }
                
                lectura = entrada.readLine();
            }
            
            if(lectura == null) {
                System.out.println("No existe tu perfil");
            }
            
            entrada.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                System.out.println("Error al leerlo");
            } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha encontrado el archivo");
        }

        return lista;
    }
}
