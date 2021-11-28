package negocio;

import daos.*;
import java.util.*;
import datos.*;
import dominio.*;
import excepciones.*;
import java.sql.*;
import dominio.Estado_Perfil_Enum;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NegocioImpl implements IntNegocio, IntPerfilNegocio {
    
    private int cantidadList = 0;
    private int cantidadPack = 0;
    private int cantidadCartasSobre = 0;
    private double precioSobres;
    private double saldoUsuario;
    
    String nombreArchivo = "PerfilesUsuarios.txt";

    
    private final IntUsuarioDao datos_usuario;
    private final IntCartaDao datos_carta;
    private final IntSobresDao datos_sobres;
    private final IntPedidoDao datos_pedidos;
    private final IntSobreUsuarioDao datos_sobre_usuario;
    private final IntCartaUsuarioDao datos_carta_usuario;
    private final IntPerfilDatos datos_perfil_usuario;

    //Constructor
    public NegocioImpl() {
        this.datos_usuario = new UsuarioDaoImpl();
        this.datos_carta = new CartaDaoImpl();
        this.datos_sobres = new SobresDaoImpl();
        this.datos_pedidos = new PedidoDaoImpl();
        this.datos_sobre_usuario = new SobreUsuarioDaoImpl();
        this.datos_carta_usuario = new CartaUsurioDaoImpl();
        this.datos_perfil_usuario = new PerfilDatosImpl();
    }
    
    Connection conexion = null;
    UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(conexion);
    Scanner entrada = new Scanner(System.in);
    
    @Override
    public void iniciarSesion(String correo, String contraseña) {
        
        try {
            Usuario checkLogin = datos_usuario.findByEmail(correo);
            
            if(correo == "" && contraseña == ""){
                System.out.println("HAS DEJADO ALGUN CAMPO VACIO, INTENTELO DE NUEVO");
                return;
                
            } else if(checkLogin == null){
                System.out.println("\nNO EXISTE EL USUARIO CON ESE EMAIL Y CONTRASEÑA");
                //AQUI IRIA EL METODO PARA CREAR UN NUEVO REGISTRO DE USUARIO
                
                System.out.println("Desea crear una cuenta\n");
                System.out.println("Introduce 'Si' o 'No'");
                
                String argumento = entrada.nextLine().toLowerCase();
                switch(argumento){
                    case "si":
                        
                        System.out.println("Introduce un correo");
                        String correoElectronico = entrada.nextLine();
                        System.out.println("Introduce una contraseña");
                        String contra = entrada.nextLine();
                        
                        Usuario usu2 = new Usuario(correoElectronico, contra, 0, false);
                        if(correo.equalsIgnoreCase("") && contraseña.equalsIgnoreCase("")){
                            System.out.println("Has dejado algun campo vacio");
                        } else {
                            datos_usuario.crearCuenta(usu2);
                            checkLogin = usu2;
                        }
                        
                        String usuName = checkLogin.getEmail();
                        String [] arrUsuName = usuName.split("@");
                        
                        System.out.println("\n || Cuenta creada - Bienvenido" + arrUsuName[0].toUpperCase() + " || \n");
                        System.out.println("\n    COLECCION FUTBOLISTAS ONLINE");
                        System.out.println("\n  --------------------------------");
                        System.out.println("\n");
                        
                        break;
                        
                    case "no":
                        System.out.println("¡¡¡¡ Debe crear un usuario para acceder a la web !!!!");
                        return;
                        
                    default:
                        System.out.println("Introduce una opcion valida");
                        return;
                }
            } else if(checkLogin.getEmail() != null){
                if(checkLogin.getPassword().equals(contraseña)){
                    
                    String usuName = checkLogin.getEmail();
                    String [] arrUsuName = usuName.split("@");
                    
                    System.out.println("\n|| Bienvenido: " + arrUsuName[0].toUpperCase() + " ||");
                    System.out.println("\n    COLECCION FUTBOLISTAS ONLINE");
                    System.out.println("\n  --------------------------------");
                    System.out.println("\n");
                    
                } else {
                    System.out.println("Contraseña incorrecta");
                    return;
                }
            }
            
            if(checkLogin.isAdministrador()){
                int opcion;
                List<Usuario> listaUsuarioAdmin = datos_usuario.listarUsuario();
                List<Carta> listaCartasAdmin = datos_carta.listarCartas();
                List<Sobres> listaSobresAdmin = datos_sobres.listarSobres();
                
                try {
                    conexion = Conexion.getConnection();
                    
                    if (conexion.getAutoCommit()){
                        conexion.setAutoCommit(false);
                    }
                    
                    do {
                        System.out.println("\n MENU ADMINISTRADOR || ELIGE UNA OPCION");
                        System.out.println("\n  Apartado usuarios: ");
                        System.out.println("        1. Ver todos los usuario");
                        System.out.println("        2. Añadir usuario");
                        System.out.println("        3. Modificar usuario");
                        System.out.println("        4. Eliminar usuario");
                        
                        System.out.println("\n  Apartado cartas: ");
                        System.out.println("        5. Ver todas las cartas");
                        System.out.println("        6. Añadir carta");
                        System.out.println("        7. Modificar carta");
                        System.out.println("        8. Eliminar carta");
                        
                        System.out.println("\n  Apartado sobres: ");
                        System.out.println("        9. Ver todos los sobres");
                        System.out.println("        10. Añadir sobre");
                        System.out.println("        11. Modificar sobre");
                        System.out.println("        12. Eliminar sobre");
                        
                        System.out.println("\n  Apartado perfiles: ");
                        System.out.println("        13. Ver todos los perfiles");
                        System.out.println("        14. Buscar un perfil");
                        
                        System.out.println("\n        0. Cerrar Sesion");
                        
                        opcion = entrada.nextInt();
                        switch(opcion){
                            
                            
                            //PARTE USUARIO
                            case 1:
                                System.out.println("\nLISTA USUARIO");
                                listaUsuarioAdmin.forEach(ListUsuario -> {
                                    System.out.println("ID Usuario: " + ListUsuario.getId_usuario() + " || Email: " + ListUsuario.getEmail() + " || Contraseña: " + ListUsuario.getPassword() + " || Saldo: " + ListUsuario.getSaldo() + " || Administrador: " + ListUsuario.isAdministrador());
                                });
                                break;
                                
                            case 2:
                                System.out.println("AÑADIR USUARIO");
                                
                                System.out.println("Introduce un correo");
                                entrada.nextLine();
                                String correo2 = entrada.nextLine();
                                
                                System.out.println("Introduce una contraseña");
                                String contra = entrada.nextLine();
                                
                                System.out.println("Introduce un saldo");
                                Double saldo = entrada.nextDouble();
                                
                                System.out.println("Es administrador\nSI = TRUE \nNO = FALSE");
                                boolean admin = entrada.nextBoolean();
                                
                                Usuario usu = new Usuario(correo2, contra, saldo, admin);
                                datos_usuario.crearCuenta(usu);
                                break;
                                
                                
                            case 3:
                                System.out.println("\nMODIFICAR USUARIO");
                                
                                System.out.println("\nQue usuario queires modificar? 'Busca mediante el ID'");
                                int id_usuario = entrada.nextInt();
                                
                                System.out.println("\nIntroduce un correo");
                                entrada.nextLine();
                                String correoModificar = entrada.nextLine();
                                
                                System.out.println("\nIntroduce una contraseña");
                                String contraModificar = entrada.nextLine();
                                
                                System.out.println("\nIntroduce un saldo");
                                Double saldoModificar = entrada.nextDouble();
                                
                                System.out.println("\nEs administrador\nTRUE o FALSE");
                                boolean adminModificar = entrada.nextBoolean();
                                
                                Usuario usu2 = new Usuario(id_usuario, correoModificar, contraModificar, saldoModificar, adminModificar);
                                //datos_usuario.modificar(usu2);
                                usuarioDaoImpl.modificar(usu2);
                                break;
                                
                            case 4:
                                System.out.println("\nELIMINAR USUARIO");
                                System.out.println("\nIntroduce el ID del usuario que deseé eliminar");
                                id_usuario = entrada.nextInt();
                                
                                Usuario usuarioBorrar = new Usuario(id_usuario);
                                //datos_usuario.borrar(id_usuario);
                                usuarioDaoImpl.borrar(usuarioBorrar);
                                break;
                                
                                
                                // PARTE CARTA
                            case 5:
                                System.out.println("\nLISTAR LAS CARTAS");
                                listaCartasAdmin.forEach(ListCartas -> {
                                    System.out.println("ID: " + ListCartas.getId_carta() + " || Nombre Jugador: " + ListCartas.getNombreJugador() + " || Equipo: " + ListCartas.getEquipo() + " || Posicion: " + ListCartas.getPosicion() + " || Media: " + ListCartas.getMedia() + " || Ataque: " + ListCartas.getAtaque() + " || Defensa: " + ListCartas.getDefensa() + " || Tipo de carta: " + ListCartas.getEdicion());
                                });
                                break;
                                
                            case 6:
                                System.out.println("\nAÑADIR CARTA");
                                System.out.println("\nIntroduce el nombre del jugador:");
                                entrada.nextLine();
                                String nombreJugador = entrada.nextLine();
                                
                                System.out.println("\nIntroduce un equipo:");
                                String equipo = entrada.nextLine();
                                
                                System.out.println("\nIntroduce la posicion:");
                                String posicion = entrada.nextLine();
                                
                                System.out.println("\nIntroduce la media:");
                                int media = entrada.nextInt();
                                
                                System.out.println("\nIntroduce el ataque:");
                                int ataque = entrada.nextInt();
                                
                                System.out.println("\nIntroduce la defensa:");
                                int defensa = entrada.nextInt();
                                
                                System.out.println("\nIntroduce la edicion de la carta");
                                entrada.nextLine();
                                String edicion = entrada.nextLine();
                                
                                Carta carta = new Carta(nombreJugador, equipo, posicion, media, ataque, defensa, edicion);
                                datos_carta.crearCarta(carta);
                                
                                break;
                                
                            case 7:
                                System.out.println("\nMODIFICAR CARTA");
                                System.out.println("\nQue carta queires modificar? 'Busca mediante el ID'");
                                int id_carta = entrada.nextInt();
                                
                                System.out.println("\nIntroduce el nombre del jugador:");
                                entrada.nextLine();
                                nombreJugador = entrada.nextLine();
                                
                                System.out.println("\nIntroduce un equipo:");
                                equipo = entrada.nextLine();
                                
                                System.out.println("\nIntroduce la posicion:");
                                posicion = entrada.nextLine();
                                
                                System.out.println("\nIntroduce la media:");
                                media = entrada.nextInt();
                                
                                System.out.println("\nIntroduce el ataque:");
                                ataque = entrada.nextInt();
                                
                                System.out.println("\nIntroduce la defensa:");
                                defensa = entrada.nextInt();
                                
                                System.out.println("\nintroduce la edicion/tipo de carta");
                                entrada.nextLine();
                                edicion = entrada.nextLine();
                                
                                carta = new Carta(id_carta, nombreJugador, equipo, posicion, media, ataque, defensa, edicion);
                                datos_carta.modificar(carta);
                                break;
                                
                            case 8:
                                System.out.println("\nELIMINAR CARTA");
                                System.out.println("\nIntroduce el ID de la carta que deseé eliminar");
                                id_carta = entrada.nextInt();
                                
                                Carta cartaEliminar = new Carta(id_carta);
                                datos_carta.eliminar(cartaEliminar);
                                break;
                                
                                
                                //PARTE SOBRES
                            case 9:
                                System.out.println("\nLISTAR LOS SOBRES");
                                listaSobresAdmin.forEach(ListSobres -> {
                                    System.out.println("ID: " + ListSobres.getId_sobres() + " || Canttidad de cartas: " + ListSobres.getCantidadCartas() + " || Nobres del sobre: " + ListSobres.getNombreSobre() + " || Precio: " + ListSobres.getPrecio());
                                });
                                break;
                                
                            case 10:
                                System.out.println("\nAÑADIR SOBRES");
                                
                                System.out.println("Introduce la cantidad de cartas que lleva el sobre:");
                                int cantidadCartas = entrada.nextInt();
                                
                                System.out.println("Introduce el nombre del sobre:");
                                entrada.nextLine();
                                String nombreSobre = entrada.nextLine();
                                
                                System.out.println("Introduce el precio:");
                                double precio = entrada.nextDouble();
                                
                                Sobres sobre = new Sobres(cantidadCartas, nombreSobre, precio);
                                datos_sobres.crearSobre(sobre);
                                break;
                                
                            case 11:
                                System.out.println("\nMODIFICAR SOBRE");
                                System.out.println("\nQue sobre queires modificar? 'Busca mediante el ID'");
                                int id_sobres = entrada.nextInt();
                                
                                System.out.println("Introduce la cantidad de cartas que lleva el sobre:");
                                cantidadCartas = entrada.nextInt();
                                
                                System.out.println("Introduce el nombre del sobre:");
                                entrada.nextLine();
                                nombreSobre = entrada.nextLine();
                                
                                System.out.println("Introduce el precio:");
                                precio = entrada.nextDouble();
                                
                                sobre = new Sobres(id_sobres, cantidadCartas, nombreSobre, precio);
                                datos_sobres.modificar(sobre);
                                break;
                                
                            case 12:
                                System.out.println("\nELIMINAR SOBRE");
                                System.out.println("\nIntroduce el ID del sobre que deseé eliminar");
                                id_sobres = entrada.nextInt();
                                
                                Sobres sobreEliminar = new Sobres(id_sobres);
                                datos_sobres.eliminar(sobreEliminar);
                                break;
                                
                            case 13:
                                System.out.println("\nListar todos los perfiles: \n");
                                
                                //System.out.println("Introduce el nombre del archivo: ");
                                //String nombreArchivo = entrada.nextLine();
                                
                                Listar(nombreArchivo);
                                break;
                                
                            case 14:
                                System.out.println("\nBuscar un perfil por correo: \n");
                                
                                //System.out.println("Introduce el nombre del archivo: ");
                                //nombreArchivo = entrada.nextLine();
                                
                                System.out.println("Introduce el correo del usuario");
                                entrada.nextLine();
                                String busqueda = entrada.nextLine();
                                
                                Buscar(nombreArchivo, busqueda);
                                break;
                                
                            case 0:
                                System.out.println("Gracias por su visita - Hasta la proxima");
                                break;
                                
                            default:
                                System.out.println("Elige una opcion valida");
                                break;
                        }
                        
                        
                        
                    } while (opcion > 0);
                    
                    
                    conexion.commit();
                    //System.out.println("COMIT HECHO");
                    
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                    
                    System.out.println("Entramos en el roll-back");
                    try {
                        conexion.rollback();
                    } catch (SQLException e) {
                        e.printStackTrace(System.out);
                    }
                    
                }
                
            } else {
                int opcion;
                int opcion_perfil;
                int opcion_tienda;
                do {
                    System.out.println("\n MENU CLIENTE || ELIGE UNA OPCION");
                    System.out.println("    1. Mi Colecccion");
                    System.out.println("    2. Mi Perfil");
                    System.out.println("    3. Tienda");
                    System.out.println("    4. Inventario");
                    System.out.println("    0. Cerrar Sesion");
                    
                    opcion = entrada.nextInt();
                    switch(opcion){
                        
                        case 1:
                            //Listar las cartas de x usuario
                            System.out.println("\n TU COLECCION DE CARTAS:");
                            System.out.println("-------------------------\n");
                            List<Carta> listarCartaDeUsuaio = datos_carta.listarCartasUsuario(checkLogin);
                            listarCartaDeUsuaio.forEach(cartaUsuario -> {
                                System.out.println("ID: " + cartaUsuario.getId_carta() + " || Nombre Jugador: " + cartaUsuario.getNombreJugador() + " || Equipo: " + cartaUsuario.getEquipo() + " || Posicion: " + cartaUsuario.getPosicion() + " || Media: " + cartaUsuario.getMedia() + " || Ataque: " + cartaUsuario.getAtaque() + " || Defensa: " + cartaUsuario.getDefensa() + " || Tipo de carta: " + cartaUsuario.getEdicion());
                            });
                            break;

                            //Perfil usuario TxT
                        case 2:
                            
                            do {
                                System.out.println("\n MENU PERFIL || ELIGE UNA OPCION");
                                System.out.println("    1. Ver mi perfil");
                                System.out.println("    2. Crear mi perfil");
                                System.out.println("    3. Modificar mi perfil");
                                System.out.println("    4. Eliminar mi perfil");
                                System.out.println("    0. Salir");
                                opcion_perfil = entrada.nextInt();
                                switch(opcion_perfil){
                                    case 1:
                                        findById(nombreArchivo, checkLogin.getId_usuario());
                                        break;
                                        
                                    case 2:
                                        System.out.println("\nIntroduce tu nombre: ");
                                        entrada.nextLine();
                                        String nombre = entrada.nextLine();
                                        
                                        System.out.println("\nIntroduce tus apellidos: ");
                                        String apellidos = entrada.nextLine();
                                        
                                        System.out.println("\nIntroduce tu ciudad: ");
                                        String ciudad = entrada.nextLine();
                                        
                                        System.out.println("\n¿Cual es tu equipo favorito?");
                                        String equipo = entrada.nextLine();
                                        
                                        System.out.println("\n¿Cual es tu estado?");
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
                                        
                                        Crear(nombreArchivo);
                                        Escribir(nombreArchivo, checkLogin.getId_usuario(), nombre, apellidos, checkLogin.getEmail(), checkLogin.getPassword(), ciudad, equipo, estado);
                                        break;
                                        
                                    case 3:
                                        System.out.println("\n ¿ Que campo quieres modificar ?");
                                        System.out.println("Campos: Nombre, Apellidos, Correo, Contraseña, Ciudad, Equipo, Estado");
                                        entrada.nextLine();
                                        String campo = entrada.nextLine().toLowerCase();
                                        
                                        // Modificamos el usuario
                                        Modificar(nombreArchivo, campo, checkLogin.getId_usuario());
                                        
                                        //
                                        EncapsularById(nombreArchivo, checkLogin.getId_usuario(), campo, checkLogin);
                                        
                                        break;
                                        
                                    case 4:
                                        Borrar(nombreArchivo, checkLogin.getId_usuario());
                                        break;
                                        
                                    case 0:
                                        System.out.println("Saliendo de tu perfil");
                                        break;
                                        
                                    default:
                                        System.out.println("Elige una opcion valida");
                                        break;
                                }
                                
                            } while (opcion_perfil > 0);
                            
                            break;
                            
                        case 3:
                            //Tienda
                            do {
                                System.out.println("\n MENU TIENDA || ELIGE UNA OPCION");
                                System.out.println("    1. Comprar sobres");
                                System.out.println("    2. Añadir saldo");
                                System.out.println("    3. Ver saldo");
                                System.out.println("    4. Ver mi pedidos");
                                System.out.println("    0. Salir");
                                opcion_tienda = entrada.nextInt();
                                switch(opcion_tienda){
                                    
                                    //Comprar sobre
                                    case 1:
                                        
                                        System.out.println("¿Que sobre quieres comprar?");
                                        //Mostrar sobres disponibles
                                        List<Sobres> listaSobresInfo = datos_sobres.listarSobres();
                                        listaSobresInfo.forEach(ListSobres -> {
                                            System.out.println("ID: " + ListSobres.getId_sobres() + " || Nobres del sobre: " + ListSobres.getNombreSobre() + " || Canttidad de cartas: " + ListSobres.getCantidadCartas() + " || Precio: " + ListSobres.getPrecio());
                                        });
                                        int id_sobre = entrada.nextInt();
                                        
                                        System.out.println("¿Cuantos sobre quieres?");
                                        int cantidad = entrada.nextInt();
                                        
                                        //Obtener precio y saldo
                                        List<Usuario> listSaldo = datos_usuario.findById(checkLogin.getId_usuario());
                                        listSaldo.forEach(saldoUsu -> {
                                            saldoUsuario = saldoUsu.getSaldo();
                                        });
                                        
                                        List<Sobres> listPrecio = datos_sobres.findByIdSobre(id_sobre);
                                        listPrecio.forEach(precioSobre -> {
                                            precioSobres = precioSobre.getPrecio();
                                        });
                                        
                                        precioSobres = precioSobres * cantidad;
                                        if (saldoUsuario > precioSobres){
                                            
                                            //Insert pedidos
                                            Pedidos pedido = new Pedidos(checkLogin.getId_usuario(), id_sobre, cantidad);
                                            datos_pedidos.comprarSobre(pedido);
                                            
                                            //insert o update en tabla sobre Sobres_en_usuario
                                            List<SobreUsuario> listarSobreUsuario = datos_sobre_usuario.listSobreUsuario(id_sobre, checkLogin.getId_usuario());
                                            if (listarSobreUsuario.isEmpty()){
                                                //No tiene el sobre ese asi que hacemos un insert con el id sobre y la cantidad
                                                SobreUsuario sobreUsu = new SobreUsuario(checkLogin.getId_usuario(), id_sobre, cantidad);
                                                datos_sobre_usuario.insertSobreUsuario(sobreUsu);
                                                
                                            } else {
                                                //Ya tiene ese sobre asi hacemos un update de la cantidad
                                                listarSobreUsuario.forEach(sobreUsuario -> {
                                                    cantidadList = sobreUsuario.getCantidad();
                                                });
                                                cantidad = cantidad + cantidadList;
                                                
                                                SobreUsuario sobreUsu = new SobreUsuario(checkLogin.getId_usuario(), id_sobre, cantidad);
                                                datos_sobre_usuario.updateSobreUsuario(sobreUsu);
                                            }
                                            
                                            //Restar saldo
                                            Usuario usu1 = new Usuario(checkLogin.getId_usuario(), checkLogin.getEmail(), checkLogin.getPassword(), checkLogin.getSaldo() - precioSobres, checkLogin.isAdministrador());
                                            try {
                                                usuarioDaoImpl.modificar(usu1);
                                            } catch (SQLException ex) {
                                                ex.printStackTrace(System.out);
                                            }
                                            
                                        } else {
                                            System.out.println("no tienes saldo suficiente");
                                        }
                                        
                                        break;
                                        
                                    case 2:
                                        //Añadir saldo
                                        System.out.println("¿Cuanto saldo quieres añadir a tu cuenta?");
                                        double saldo = entrada.nextDouble();
                                        Usuario usu1 = new Usuario(checkLogin.getId_usuario(), checkLogin.getEmail(), checkLogin.getPassword(), checkLogin.getSaldo() + saldo, checkLogin.isAdministrador());
                                        try {
                                            usuarioDaoImpl.modificar(usu1);
                                        } catch (SQLException ex) {
                                            ex.printStackTrace(System.out);
                                        }
                                        break;
                                        
                                    case 3:
                                        //Ver saldo
                                        List<Usuario> verSaldo = datos_usuario.findById(checkLogin.getId_usuario());
                                        verSaldo.forEach(saldoUsu -> {
                                            System.out.println("Tienes: " + saldoUsu.getSaldo() + "€");
                                        });
                                        break;
                                        
                                    case 4:
                                        //Ver mi pedidos
                                        List<Pedidos> listarpedidosUsuario = datos_pedidos.listarPedidos(checkLogin);
                                        System.out.println("\nUsuario: " + checkLogin.getEmail());
                                        listarpedidosUsuario.forEach(pedidoUsuario -> {
                                            System.out.println("ID Pedido: " + pedidoUsuario.getId_pedido() + " || ID Sobre: " + pedidoUsuario.getId_sobres()+ " || Cantidad: " + pedidoUsuario.getCantidad() + " || Fecha Alta: " + pedidoUsuario.getFecha_alta());
                                        });
                                        break;
                                        
                                    case 0:
                                        System.out.println("Saliendo de tu perfil");
                                        break;
                                        
                                    default:
                                        System.out.println("Elige una opcion valida");
                                        break;
                                }
                                
                            } while (opcion_tienda > 0);
                            
                            break;
                            
                        case 4: //INEVNTARIO
                            System.out.println("\nTus sobres ordenados por ID de forma ASC\n");
                            
                            //Listar sobres en usuario
                            List<SobreUsuario> listaSobrePorUsu = datos_sobre_usuario.findByUsuario(checkLogin.getId_usuario());
                            listaSobrePorUsu.forEach(sobreUsuario -> {
                                try {
                                    //Listar datos de un sobre
                                    List<Sobres> listByID = datos_sobres.findByIdSobre(sobreUsuario.getId_sobres());
                                    listByID.forEach(isSobre ->{
                                        System.out.println("ID Sobre: " + sobreUsuario.getId_sobres() + " || Nombre Sobre: " + isSobre.getNombreSobre() + " || Cantidad: " + sobreUsuario.getCantidad());
                                    });
                                } catch (LecturaEx ex) {
                                    ex.printStackTrace(System.out);
                                }
                            });
                            
                            //Preguntar si quiere abrir un sobre
                            System.out.println("\n¿Quieres abrir un sobre? Si o No");
                            entrada.nextLine();
                            String opcion2 = entrada.nextLine().toLowerCase();
                            
                            switch (opcion2){
                                
                                case "si":
                                    System.out.println("\n¿Que sobre quieres abrir? Introduce el ID del sobre");
                                    int openening = entrada.nextInt();
                                    
                                    /***
                                     * 
                                     * Update restando 1 a la cantidad de los sobres
                                     * Coger la cantidad de cartas que hay en ese sobre y sacar aleatoriamente numeros, cada numero corresponde a un id_carta y guardarlo en un List/Array
                                     * Comprobar si esa carta la tiene el usuario, si la tiene:
                                     * - 1. Sacarla como repetida (Creo que voy a hacer esta opcion), si esta repetida lo sacamos en la consola y no gaurdamos en la bbdd ni nada.(Las repetidas podria meterlas en un txt)
                                     * - 2. Sacar otro numero aleatorio
                                     * 
                                     ***/
                                    
                                    //Sacar la cantidad de sobre que tiene el usuario
                                    List<SobreUsuario> packOpening = datos_sobre_usuario.listSobreUsuario(openening, checkLogin.getId_usuario());
                                    packOpening.forEach(listPack -> {
                                        cantidadPack = listPack.getCantidad();
                                    });
                                    
                                    
                                    //Update cantidad de los sobres en usuario
                                    SobreUsuario updateOpening = new SobreUsuario(checkLogin.getId_usuario(), openening, (cantidadPack - 1));
                                    datos_sobre_usuario.updateSobreUsuario(updateOpening);
                                    
                                    // Sacar la cantidad de cartas en ese sobre
                                    List<Sobres> listByID = datos_sobres.findByIdSobre(openening);
                                    listByID.forEach(isSobre ->{
                                        cantidadCartasSobre = isSobre.getCantidadCartas();
                                    });
                                    
                                    int arraySobre[];
                                    arraySobre = new int[cantidadCartasSobre];
                                    
                                    //Sacar el id maximo que tengo de cartas en la bbdd
                                    //System.out.println("ID MAX: " + datos_carta.listMaxID()); //Imprimimos el metodo que te da el id maximo de la tabla carta
                                    //int maxIdCarta = datos_carta.listMaxID();
                                    
                                    //Sacar tantos numeros random como cantidad de cartas tenga el sobre
                                    //int numRandom = (int) (Math.random() * datos_carta.listMaxID() + 1);
                                    
                                    // Asignar un numero aleatorio a cada posicion del array
                                    for (int i = 0; i < arraySobre.length; i++) {
                                        arraySobre[i] = (int) (Math.random() * datos_carta.listMaxID() + 1);
                                    }
                                    
                                    // Recorer el array y asigna a cada num una carta
                                    for (int i = 0; i < arraySobre.length; i++) {
                                        
                                        System.out.println("Carta " + (i + 1) + ":");
                                        List<Carta> foundByID = datos_carta.FindById(arraySobre[i]);
                                        foundByID.forEach(isCarta ->{
                                            System.out.println("    Nombre Jugador: " + isCarta.getNombreJugador() + " || Equipo: " + isCarta.getEquipo() + " || Posicion: " + isCarta.getPosicion() + " || Media: " + isCarta.getMedia() + " || Ataque: " + isCarta.getAtaque() + " || Defensa: " + isCarta.getDefensa() + " || Edición: " + isCarta.getEdicion());
                                        });
                                        
                                        List<CartaUsuario> listarSobreUsuario = datos_carta_usuario.listCartaUsuario(checkLogin.getId_usuario(), arraySobre[i]);
                                        if (listarSobreUsuario.isEmpty()){
                                            //No tiene la carta, se la ingresamos al usuario
                                            CartaUsuario cartaUsu = new CartaUsuario(checkLogin.getId_usuario(), arraySobre[i]);
                                            datos_carta_usuario.insertarCartaUsuario(cartaUsu);
                                            System.out.println("\n");
                                            
                                        } else {
                                            //Ya tiene esa carta
                                            System.out.println("    ¡CARTA REPETIDA!\n");
                                        }
                                        
                                        //System.out.println((i) + " :" + arraySobre[i]);
                                    }
                                    
                                    break;
                                    
                                case "no":
                                    System.out.println("\nVolviendo al menú principal");
                                    break;
                                    
                                default:
                                    System.out.println("\nElige una opcion valida");
                                    break;
                            }
                            
                            break;
                            
                        case 0:
                            System.out.println("\nGracias por su visita - Hasta la proxima");
                            break;
                            
                        default:
                            System.out.println("\nElige una opcion valida");
                            break;
                    }
                    
                } while (opcion > 0);
                
            }
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
        
    }

    @Override
    public int crearCuenta(Usuario usuario) {
        int registro = 0;
        try {
            datos_usuario.crearCuenta(usuario);
            System.out.println("¡Cuenta creada con exito!");
        } catch (EscrituraEx ex) {
            ex.printStackTrace(System.out);
        }
        return registro;
    }

    // ARCHIVO TXT
    @Override
    public void Crear(String nombreArchivo) {
        try {
            if(datos_perfil_usuario.Existe(nombreArchivo) == false){
                datos_perfil_usuario.Crear(nombreArchivo);
            } else {
                System.out.println("El archivo ya existe");
            }
            
        } catch (AccesoDatosEx e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void Listar(String nombreArchivo) {
        try {
            List<UsuarioPerfil> lista = datos_perfil_usuario.Listar(nombreArchivo);

            if (lista.isEmpty()){
                System.out.println("No hay ningun perfil creado");
            } else {
                lista.forEach(perfil -> {
                    System.out.println("ID Usuario: " + perfil.getId_usuario() + " || Nombre: " + perfil.getNombre() + " || Apellidos: " + perfil.getApellidos() + " || Correo: " + perfil.getCorreo() + " || Contraseña: " + perfil.getPsw() + " || Ciudad: " + perfil.getCiudad() + " || Equipo: " + perfil.getEquipo() + " || Estado: " + perfil.getEstado());
                });
            }
        } catch (LecturaEx ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void Buscar(String nombreArchivo, String buscar) {
        try {
            System.out.println(datos_perfil_usuario.Buscar(nombreArchivo, buscar));
        } catch (LecturaEx e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void findById(String nombreArchivo, int buscar) {
        try {
            System.out.println(datos_perfil_usuario.findById(nombreArchivo, buscar));
        } catch (LecturaEx e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void Escribir(String nombreArchivo, int id_usuario, String nombre, String apellidos, String correo, String psw, String ciudad, String equipo, String estado) {
        try {
            UsuarioPerfil newPerfil = new UsuarioPerfil(id_usuario, nombre, apellidos, correo, psw, ciudad, equipo, estado);
            if(datos_perfil_usuario.Existe(nombreArchivo) == true){
                datos_perfil_usuario.Escribir(newPerfil, nombreArchivo, true);
            } else {
                System.out.println("El archivo no existe");
            }
            
        } catch (EscrituraEx ex) {
            ex.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void Borrar(String nombreArchivo, int buscar) {
        datos_perfil_usuario.Borrar(nombreArchivo, buscar);
    }

    @Override
    public void Modificar(String nombreArchivo, String campo, int id_usuario) {
        try {
            datos_perfil_usuario.Modificar(nombreArchivo, campo, id_usuario);
        } catch (EscrituraEx ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void EncapsularById(String nombreArchivo, int buscar, String campo, Usuario checkLogin) {
        
        try {
            List<UsuarioPerfil> list = datos_perfil_usuario.EncapsularById(nombreArchivo, buscar, campo);
            list.forEach(perfil -> {
                if (campo.equalsIgnoreCase("correo")){
                    try {
                        Usuario usuario = new Usuario(checkLogin.getId_usuario(), perfil.getCorreo(), perfil.getPsw(), checkLogin.getSaldo(), false);
                        datos_usuario.modificar(usuario);
                    } catch (SQLException ex) {
                        ex.printStackTrace(System.out);
                    }
                } else if (campo.equalsIgnoreCase("contraseña")){
                    try {
                        Usuario usuario = new Usuario(checkLogin.getId_usuario(), perfil.getCorreo(), perfil.getPsw(), checkLogin.getSaldo(), false);
                        datos_usuario.modificar(usuario);
                    } catch (SQLException ex) {
                        ex.printStackTrace(System.out);
                    }
                }
            });
            
        } catch (LecturaEx ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    
}
