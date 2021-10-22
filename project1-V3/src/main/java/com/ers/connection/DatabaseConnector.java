package com.ers.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a modular way to connect to the database
 */
public final class DatabaseConnector {

    private static Logger logger = LogManager.getLogger(DatabaseConnector.class);

    /* This static block makes Tomcat know which database driver to use */
    static
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e)
        {
            logger.warn("Class not found exception thrown when adding Postgres driver.", e);
        }
    }

    /**
     * Gets the connection to JDBC and provide an easy way to grab connections using a static method
     * @return Connection   returns a JDBC connection object
     */
    public static Connection getConnection() throws SQLException
    {
        String url = "jdbc:postgresql://javafs-210907-rds.csznvnr7exd6.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "postgres";
        String password = "2022AmazingCareer";
        return DriverManager.getConnection(url, username, password);

    }

}
