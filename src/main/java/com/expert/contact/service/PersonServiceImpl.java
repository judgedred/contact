package com.expert.contact.service;

import com.expert.contact.dao.DaoException;
import com.expert.contact.dao.DaoFactory;
import com.expert.contact.dao.DaoFactoryImpl;
import com.expert.contact.dao.PersonDao;
import com.expert.contact.domain.Person;

import java.io.*;
import java.util.List;


public class PersonServiceImpl implements PersonService
{
    private String importPath;
    private BufferedReader br = null;
    private PersonDao personDao;

    public String getImportPath()
    {
        return importPath;
    }

    public void setImportPath(String importPath)
    {
        this.importPath = importPath;
    }

    @Override
    public void create() throws DaoException, IOException
    {
        try
        {
            this.setImportPath("d://java/practice/contact/contact.csv");
            String line = "";
            String csvSplit = ",";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d://java/practice/contact/contact.csv"), "UTF8"));
            while((line = br.readLine()) != null)
            {
                String[] contact = line.split(csvSplit);
                Person person = new Person();
                person.setPersonName(contact[0]);
                person.setPersonSurname(contact[1]);
                person.setLogin(contact[2]);
                person.setEmail(contact[3]);
                person.setPhoneNumber(Long.parseLong(contact[4]));
                personDao.create(person);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(br != null)
            {
                br.close();
            }
        }

    }

    @Override
    public void update(Person person) throws DaoException
    {

    }

    @Override
    public List<Person> getPersonAll() throws DaoException
    {
        return null;
    }

    @Override
    public void close() throws DaoException
    {
        if(personDao != null)
        {
            personDao.close();
        }
    }

    public PersonServiceImpl() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        personDao = daoFactory.getPersonDao();
    }
}
