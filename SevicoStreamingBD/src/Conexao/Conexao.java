/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Duwann
 */
public class Conexao {
    public String url = "jdbc:mysql://localhost:3306/streaming_db2"; //Nome da base de dados
    public String user = "Duwann"; //nome do usuário do MySQL
    public String password = "31"; //senha do MySQL 
    
    
    public Connection conectar() {
        try {

            Connection con =  DriverManager.getConnection(url,user,password);
            return con;
            
      } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

}
