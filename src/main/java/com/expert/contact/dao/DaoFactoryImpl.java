package com.expert.contact.dao;


import java.sql.Connection;
import java.sql.DriverManager;

public class DaoFactoryImpl implements DaoFactory
{
    private String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/contact";
    private static String user = "admin";
    private static String pass = "admin";

    public static Connection getConnection() throws DaoException
    {
        try
        {
            return DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public PersonDao getPersonDao() throws DaoException
    {
        return new PersonDaoImpl();
    }

    public DaoFactoryImpl() throws DaoException
    {
        try
        {
            Class.forName(driver);
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }
}
