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
    private PersonDao personDao;
    BufferedReader br = null;

    @Override
    public boolean importPerson(InputStream csvFileStream, String csvSplit) throws DaoException, IOException
    {
        try
        {
            String line = "";
            br = new BufferedReader(new InputStreamReader(csvFileStream, "UTF8"));
            List<Person> personList = personDao.getPersonAll();
            int i = 1;
            while((line = br.readLine()) != null)
            {
                boolean personIsNew = true;
                String[] contact = line.split(csvSplit);
                Person person = new Person();
                person.setPersonName(contact[0]);
                person.setPersonSurname(contact[1]);
                person.setLogin(contact[2]);
                person.setEmail(contact[3]);
                person.setPhoneNumber(Long.parseLong(contact[4]));
                for(Person p : personList)
                {
                    if(p.getLogin().equals(person.getLogin()))
                    {
                        personIsNew = false;
                        person.setPersonId(p.getPersonId());
                        break;
                    }
                }
                if(personIsNew)
                {
                    personDao.createAddBatch(person);
                }
                else
                {
                    personDao.updateAddBatch(person);
                }
                if(i == 1000)
                {
                    personDao.runBatch();
                    i = 0;
                }
                i++;
            }
            personDao.runBatch();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
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
    public List<Person> getPersonAllPaging(Integer offset, Integer recordsQuantity) throws DaoException
    {
        return personDao.getPersonAllPaging(offset, recordsQuantity);
    }

    @Override
    public List<Person> getPersonAllPagingSort(String sortValue, Integer offset, Integer recordsQuantity) throws DaoException
    {
        return personDao.getPersonAllPagingSort(sortValue, offset, recordsQuantity);
    }

    @Override
    public List<Person> getPersonAll() throws DaoException
    {
        return personDao.getPersonAll();
    }

    @Override
    public Integer getRecordsQuantity()
    {
        return personDao.getRecordsQuantity();
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
