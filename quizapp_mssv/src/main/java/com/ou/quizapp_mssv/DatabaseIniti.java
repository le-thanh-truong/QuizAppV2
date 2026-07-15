/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.quizapp_mssv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DatabaseIniti {
    public static void initDB(){
        try {
            Connection connection = JdbcConnector.getInstance().connect();
            
            if(!connection.isClosed())
                System.out.println("Ket noi CSDL thanh cong");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseIniti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
