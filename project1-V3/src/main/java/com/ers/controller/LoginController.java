package com.ers.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface LoginController
{
    Object login(HttpServletRequest request) throws IOException;
    Object logout(HttpServletRequest request);
}
