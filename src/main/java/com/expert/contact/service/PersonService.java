package com.expert.contact.service;


import com.expert.contact.dao.DaoException;
import com.expert.contact.domain.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PersonService
{
    public boolean importPerson(InputStream csvFileStream, String csvSplit) throws DaoException, IOException;
    public void update(Person person) throws DaoException;
    public List<Person> getPersonAll() throws DaoException;
    public List<Person> sortPerson(String sortValue) throws DaoException;
    public void close() throws DaoException;
}
