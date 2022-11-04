package laboratorio;

import java.util.logging.Logger;
import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;

public class Laboratorio {

    public static void main(String[] args) {
        // Se crean variables que almacenen la informacion necesaria para la cadena de conexion
        //Variable del servidor
        String servidor = "jdbc:mysql://localhost:3307/prueba";
        //Variable del Usuario
        String usuario = "root";
        //Variable de la contraseña
        String password = "";
        //objeto del tipo Connection para administrar la conexión
        Connection conexion;
        //objeto del tipo Statement para dar instrucciones al compilador
        Statement declaracion;
        //objeto del tipo resultSet para visualizar todos los datos
        ResultSet datosTabla;
        try {
            //registrar el driver de conexión para la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            //Encapsulamiento de Try Catch, para amnejo de excepciones
            Logger.getLogger(Laboratorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //acceder a la base de datos para almacenar nueva informacion
            conexion = DriverManager.getConnection(servidor, usuario, password);
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("INSERT INTO persona VALUES (null,'Gabriel García Márquez','1927-03-06')");

            //Leer Datos
            datosTabla = declaracion.executeQuery("SELECT * FROM persona");
            datosTabla.next();
            do {
                //imprimir en consola la informacion de la base de datos, recorriendo mientras haya registros
                System.out.println(datosTabla.getString("id") + ". Nombre: " + datosTabla.getString("nombre") + "\n Nacimiento: " + datosTabla.getString("nacimiento"));
            } while (datosTabla.next());
            //terminar la conexion a la base de datos
            conexion.close();
        } catch (SQLException ex) {
            //Encapsulamiento de Try Catch, para amnejo de excepciones
            Logger.getLogger(Laboratorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
