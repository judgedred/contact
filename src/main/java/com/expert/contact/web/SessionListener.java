package com.expert.contact.web;


import com.expert.contact.dao.DaoException;
import com.expert.contact.service.PersonService;
import com.expert.contact.service.PersonServiceImpl;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener
{
    @Override
    public void sessionCreated(HttpSessionEvent event)
    {
        try
        {
            PersonService personService = new PersonServiceImpl();
            event.getSession().setAttribute("personService", personService);
        }
        catch(DaoException d)
        {
            d.printStackTrace();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event)
    {
        try
        {
            PersonService personService = (PersonService)event.getSession().getAttribute("personService");
            if(personService != null)
            {
                personService.close();
            }
        }
        catch(DaoException d)
        {
            d.printStackTrace();
        }
    }
}
