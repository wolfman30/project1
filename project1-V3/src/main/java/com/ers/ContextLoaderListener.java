package com.ers;

import com.ers.connection.DBConnector;
import com.ers.connection.GeneralDBConnector;
import com.ers.repository.Repository;
import com.ers.repository.RepositoryPostgres;
import com.ers.service.UserService;
import com.ers.service.UserServiceAlpha;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * This enables all servlets in the application server to know
 * how to connect to the postgresql database
 */
public class ContextLoaderListener implements ServletContextListener {

    private static Logger logger = LogManager.getLogger(RepositoryPostgres.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        logger.debug("Initializing servlet context");
        ServletContext context = servletContextEvent.getServletContext();

        String url = context.getInitParameter("DB_URL");
        String username = context.getInitParameter("DB_USERNAME");
        String password = context.getInitParameter("DB_PASSWORD");
        String driverName = context.getInitParameter("DB_DRIVER");

        UserService userService = UserServiceAlpha.getInstance();
        context.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {

    }
}
