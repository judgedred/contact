package com.expert.contact.web;


import com.expert.contact.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PersonController extends HttpServlet
{
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        HttpSession session = request.getSession();

        if(url.equals("/main"))
        {
            try
            {
                PersonService personService = (PersonService) session.getAttribute("personService");
                personService.create();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/main.jsp");
            dispatcher.forward(request, response);
        }
    }

}
