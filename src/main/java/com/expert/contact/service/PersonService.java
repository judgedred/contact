package com.expert.contact.service;


import com.expert.contact.dao.DaoException;
import com.expert.contact.domain.Person;

import java.io.IOException;
import java.util.List;

public interface PersonService
{
    public void create() throws DaoException, IOException;
    public void update(Person person) throws DaoException;
    public List<Person> getPersonAll() throws DaoException;
    public void close() throws DaoException;
}
