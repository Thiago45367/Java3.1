/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
     public static Connection getConnection() throws Exception {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/animaldb", "root", "sua_senha");
    }
}
