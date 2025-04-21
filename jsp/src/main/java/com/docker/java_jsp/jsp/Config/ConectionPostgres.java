package com.docker.java_jsp.jsp.Config;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConectionPostgres {
    Connection conection;
    String URL = "jdbc:postgresql://localhost:5432/web?user=admin&password=admin";

    public ConectionPostgres() {
        try {
            conection = DriverManager.getConnection(URL);

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    public Connection getConection() {
        return conection;
    }
}
