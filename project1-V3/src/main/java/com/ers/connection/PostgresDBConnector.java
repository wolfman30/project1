package com.ers.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creating connection specific to postgresql database
 */
public class PostgresDBConnector implements DBConnector{

    private static Logger logger = LogManager.getLogger(PostgresDBConnector.class.getName());
    /*
    * Preload the jdbc driver
     */
    static
    {
        try
        {
            DriverManager.registerDriver(new Driver());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.debug("Could not initialize the jdbc postgresql driver");
            throw new RuntimeException("Could not initialize the jdbc driver");
        }
    }

    @Override
    public Connection newConnection(String username, String password, String url) throws SQLException
    {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public Connection newConnection() throws SQLException
    {
        return null;
    }
}
