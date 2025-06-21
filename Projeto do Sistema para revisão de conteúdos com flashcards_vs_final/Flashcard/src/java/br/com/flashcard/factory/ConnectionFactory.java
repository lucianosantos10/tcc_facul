
package br.com.flashcard.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

 private static final String USUARIO = "root";
 private static final  String SENHA = "";
 private static final  String CAMINHO = "jdbc:mysql://localhost:3306/db_flashcard";
    
   static Connection con;
 
    public static Connection conectar() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(CAMINHO, USUARIO, SENHA);
            System.out.println("Conexão realizada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro na conexão:" + ex);
        }
        return con;
    }


    public static Connection desconectar() {
        try {
            con.close();
            System.out.println("Conexão encerrada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro na conexão:" + ex);
        }
        return con;
    }
    
    
    public static void main(String[] args) {
        
        con = ConnectionFactory.conectar();
        con = ConnectionFactory.desconectar();
    } 

}
 