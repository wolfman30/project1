package com.ers.controller;


import com.ers.model.Employee;
import com.ers.model.User;

import com.ers.repository.RepositoryPostgres;
import com.ers.service.UserService;
import com.ers.service.UserServiceAlpha;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.Log;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Good ole Singleton pattern. I figure it is better to only initialize this once just like the service
 * and other classes that don't need to be initialized multiple times
 */
public class LoginControllerAlpha implements LoginController
{
    private static Logger logger = LogManager.getLogger(LoginControllerAlpha.class.getName());

    private static LoginControllerAlpha loginController;

    private LoginControllerAlpha() {}

    public static LoginControllerAlpha getInstance()
    {
        if (loginController == null)
        {
            loginController = new LoginControllerAlpha();
        }

        return loginController;
    }

    /**
     * Used to get ther result of the user logging in, which results in one
     * of three login results: (1) log in unsuccessful from incorrect email or password
     * (2) login successful with user as type employee, (3) log in successful with user
     * as type manager
     * @param request      Http request
     * @return login_result  Hashmap of login result
     */
    @Override
    public Map<String, User> login(HttpServletRequest request) throws IOException {

        UserService service = UserServiceAlpha.getInstance();

        /* Hashmap for the login result for cleaner code in dispatcher servlet*/
        Map<String, User> login_result = new HashMap<>();

        /*Parsing JSON string from fetch() */
        String[] unparsed_creds = request.getReader().lines().collect(Collectors.joining()).split(",");
        logger.debug("unparsed creds: " + unparsed_creds.toString());

        String email = unparsed_creds[0].substring(1).split(":")[1];
        email = email.substring(1, email.length() -1);

        String password = unparsed_creds[1].substring(0, unparsed_creds[1].length() - 1).split(":")[1];
        password = password.substring(1, password.length() -1);

        logger.debug("Email is: " + email);
        logger.debug("Password is: " + password);

        User loggedUser = service.authenticate(email, password);

        /**
         * logic to return string based on unsuccesful log in, user of type
         * employee, or user of type manager for the dispatcher servlet to
         * route accordingly
         */
        if (loggedUser == null) {
            login_result.put("incorrect email or password", loggedUser);
        }
        else if (loggedUser.getUserType().equals("employee")){
            login_result.put("employee logged in successfully", loggedUser);
        }
        else if (loggedUser.getUserType().equals("manager"))
        {
            login_result.put("manager logged in successfully", loggedUser);
        }

        request.getSession().setAttribute("loggedUser", loggedUser);

        return login_result;
    }

    @Override
    public Map logout(HttpServletRequest request) {
        request.getSession().invalidate();
        Map logged_out = new HashMap<>();
        logged_out.put("logged out", "/index.html");
        return logged_out;

    }
}
