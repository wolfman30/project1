package com.ers.connection;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Used to establish connection to the database
 */
public class GeneralDBConnector implements DBConnector
{
    private String username;
    private String password;
    private String url;
    private String drivername;

    private static Properties props = new Properties();

    public GeneralDBConnector() {

    }

    /**
     * For connecting to the database
     * @param username  my username for the database
     * @param password  my password for the database
     * @param url       the url to the AWS RDS instance where the database is stored
     * @param driverName    the driver name for the specific database
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public GeneralDBConnector(String username, String password, String url, String driverName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        this.username = username;
        this.password = password;
        this.url = url;
        this.drivername = driverName;
        DriverManager.registerDriver((Driver)Class.forName(this.drivername).newInstance());
    }
    @Override
    public Connection newConnection(String username, String password, String url) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public Connection newConnection() throws SQLException
    {
        return this.newConnection(this.username, this.password, this.url);
    }
}
