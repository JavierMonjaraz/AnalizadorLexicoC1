package com.example.proyecto.DbDriver;

import java.sql.*;
import java.util.Properties;

public class Connector {
    private static Connector connector;
    private Connection connection;

    private Connector() {
    }

    public static Connector getConnector() {
        if(connector==null) {
            connector = new Connector();
        }
        return connector;
    }

    public Connection setConnection() {
        if(connection==null) {
            String connectionString = "jdbc:mysql://localhost:3306";
            String user = "mantenimiento";
            String password = "mantenimiento8a";
            Properties props =  new Properties();
            props.put("user",user);
            props.put("password",password);
            props.put("allowMultiQueries","true");
            //Connection connection = null;
            try {
                connection = DriverManager.getConnection(connectionString, props);
//                System.out.println(connection.getClass().getCanonicalName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
