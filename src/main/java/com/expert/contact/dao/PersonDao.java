package com.expert.contact.dao;


import com.expert.contact.domain.Person;

import java.util.List;

public interface PersonDao
{
    public Person create(Person person) throws DaoException;
    public void update(Person person) throws DaoException;
    public List<Person> getPersonAll() throws DaoException;
    public Person getPersonById(int id) throws DaoException;
    public void delete(Person person) throws DaoException;
    public void close() throws DaoException;
}
