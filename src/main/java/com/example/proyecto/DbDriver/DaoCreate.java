package com.example.proyecto.DbDriver;

import java.sql.*;

public class DaoCreate {
    private Connection connection = null;
    private final int ACCEPT = 1;

    public DaoCreate() {
        Connector connector = Connector.getConnector();
        connection = connector.setConnection();
    }

    public boolean ExecuteQuery(String query) {
        boolean resultado = false;
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                if(statement.executeUpdate(query) == ACCEPT){
                    resultado = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return resultado;
    }


}
