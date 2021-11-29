# Proyecto - 1º Evaluacion

PSP &amp; Acceso a Datos

Descripción breve:

Programa con Java para las asignaturas psp y acceso a datos.

# Índice

- [Introducción](#1)

- [Modelo de datos](#_Toc89017173)

- [Diagrama de clases](#_Toc89017174)

- [Funcionamiento del programa](#_Toc89017175)

- [Explicación de los métodos](#_Toc89017176)

- [Siguientes pasos](#_Toc89017177)

- [Conclusiones](#_Toc89017178)


## Introducción (#id=1)

Mi idea para el proyecto es una colección de cromos de futbolistas, la cual para ir aumentando su colección tiene que comprar sobres.

Para poder ingresar en el programa necesitas tener una cuenta obligatoriamente, los cuales se dividen en dos roles: administradores o clientes, si el usuario es un cliente se le despliega un menú distinto al del administrador con el cual puede gestionar los usuario, cartas, sobres y pedidos. Por otra parte, el cliente tendrá un menú con el cual podrá ver su colección, acceder a la tienda, ver su inventario y su perfil de usuario.

## Modelo de datos

Mi programa lo he dividido en 6 paquetes:

- Controlador:
  - Es la encargada de iniciar el programa mediante la clase Test.java

- Daos:
  - Este paquete tiene las interfaces y implementaciones de los daos

- Datos:
  - Incluye la clase Conexión.java y las clases datos del archivo txt

- Dominio:
  - Tiene las clases objeto

- Excepciones:
  - Con este paquete controlamos el uso de las excepciones

- Negocio
  - Es la encargada del manejo lógico de la aplicación

![Imagen del Diagrama de Clases](https://raw.githubusercontent.com/IgnacioPerez12/Proyecto_1Evaluacion_Cromos/master/recursos/paquetes.png)

## Diagrama de clases

![Imagen del Diagrama de Clases](https://raw.githubusercontent.com/IgnacioPerez12/Proyecto_1Evaluacion_Cromos/master/recursos/Diagrama_Clases.png)


## Funcionamiento del programa

El programa para poder acceder a el te solicita una cuenta o, sino que te crees una, una vez has iniciado sesion el programa analiza si el usuario es un administardor o un cliente, dependiendo de eso te muestra funciones distintas:


- **Administrador:**

Es el encargado de hacer las funciones CRUD:

(Listar, añadir, modificar, eliminar).

Dividido en tres bloques con las mismas funciones para las diferentes tablas o archivos de texto:

    - Usuarios
    - Cartas
    - Sobres
    - Perfil Usuario


- **Cliente:**

El apartado cliente se divide en 4 opciones:

Mi Coleccion: Muestra todas las cartas que tiene el usuario en su posesion.

Mi Perfil: En el perfil del usuario podra ver su perfil y si no tiene crearlo, tambien podra modificarlo o eliminarlo. Todos los perfiles se almacena en un archivo txt.

Tienda: El apartado tienda se divide en 4 opciones más, las opcion 1 y 2 serian las opciones principales. Las demas opciones permiten al usuario obtener informacion de su cuenta.

Inventario: Permite al usaurio administrar sus sobre y abrirlos cuando el deseé aumentando asi su colección de cromos.



## Explicación de los métodos

**Perfil**

Ver mi perfil: Para imprimir el perfil de un usuario especifico he implementado el metodo Buscar() mediante el ID que lo saco del inicio de sesion.

Crear mi perfil: Para crear el perfil, primero compruebo que le archivo txt existe sino lo creo. Una vez comprobado le pido al usuario que meta los valores de los campos y elija un estado de ánimo que se introduce mediante un enum.

Con toda la informacion llamamos al metodo Escribir() y le pasamos los datos que escribe en una nueva linea del txt

Modificar mi perfil: Para modificar el perfil al usuario le pedimos que campo quiere modificar, una vez teniendo el campo, hacemos una busqueda del usuario mediante la ID y nos creamos un nuevo perfilUsuario para poder manupularlo.

Realizamos un switch con los case de los distintos campos del perfil, caundo entre en algun case llamamos al usuario y seteamos ese campo por el que nos mete mediante teclado.

Si el campo elegido por el cliente es el correo o la contraseña ademas de modificar el archivo txt realizamos un update en la tabla de la base de datos.

Eliminar mi perfil: El metodo borrar lo que se encarga es de eliminar la linea donde esta el perfil del usuario. Para hacer eso lo que hacemos es una comparacion de la lectura linea por linea con la busqueda del ID que hemos cogido del inicio de sesion.

Si la busqueda es == a la lectura esa linea la salta y no la escribe en un nuevo archivo, al terminar la lectura elimina el archivo anterior y se queda el nuevo con el mismo nombre.

**Tienda**

Comprar sobres: Para comprar un sobre al usuario le mostramos como forma de guia todos los sobres disponibles con un list y le pedimos por teclado el id del sobre, una vez selecciona uno le pedimos la cantidad de sobres que quiere comprar.

Para poder restar el precio del sobre al saldo mediante list obtenemos los dos valores y los encapsulamos. Luego creamos un objeto usuario y le restamos el saldo en el campo del saldo, y usamos el metodo modificar().

Hacemos otro list a la tabla sobre\_en\_usuario para saber si ese sobre ya lo tiene el usuario, si lo tiene sumamos los que tiene a la cantida de antes, si no lo tiene hacemos un insert con el sobre y la cantidad.

Añadir saldo: Para añadir saldo al usuario le pedimos que nos meta por teclado la cantidad de saldo que quiere meter, teniendo el saldo creamos un nuevo objeto de tipo usuario en el cual metemos mediante los getter los valores del usuario y en el saldo sumamos el saldo que nos ha metido por teclado.

Ver saldo: Para el saldo del usuario hacemos un SELECT con un WHERE en el cual le pedimos el ID\_USUARIO que ya lo tenemos del inicio de sesion.

Ver mi pedido: Para mostrar los pedidos de un usuario hacemos un SELECT con un WHERE en el cual le pedimos el ID\_USUARIO que ya lo tenemos del inicio de sesion.

## Siguientes pasos

- Se podía implementar una opción de cromos repetidos y guardarlos en un txt o en una tabla de sql.

- Menú admin: Poder borrar el archivo txt que quiera por completo.

- Cambiar algunas funciones ya que mediante una interfaz se puede hacer mediante botones o inputs.

- La conexión Transaccional no me funciono como lo deseaba hace los execute antes del commit. Mirar la implementación y solucionarlo.

- A la hora de abrir un sobre si meto un id que no existe me abre igualmente

- Manejar la cantidad de los sobres en los usuarios; Si llega a 0 que no pueda abrirlo

## Conclusiones

Me parece más sencillo manejar información mediante sql que mediante archivos de txt, aunque visualizarlo por la consola limita mucho algunas funciones y lo hace más lioso.


