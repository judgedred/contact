package com.expert.contact.dao;


public interface DaoFactory
{
    public PersonDao getPersonDao() throws DaoException;
}
