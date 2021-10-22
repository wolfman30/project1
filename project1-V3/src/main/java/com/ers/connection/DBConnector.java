package com.ers.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public interface DBConnector
{
    Connection newConnection(String username, String password, String url) throws SQLException;
    Connection newConnection() throws SQLException;
}
