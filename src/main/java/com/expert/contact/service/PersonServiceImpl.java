package com.expert.contact.service;

import com.expert.contact.dao.DaoException;
import com.expert.contact.dao.DaoFactory;
import com.expert.contact.dao.DaoFactoryImpl;
import com.expert.contact.dao.PersonDao;
import com.expert.contact.domain.Person;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
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
    public boolean importPerson(InputStream csvFileStream, String csvSplit) throws DaoException, IOException
    {
        try
        {
            String line = "";
//            String csvSplit = ",";
            BufferedReader br = new BufferedReader(new InputStreamReader(csvFileStream, "UTF8"));
            while((line = br.readLine()) != null)
            {
                boolean personIsNew = true;
                List<Person> personList = personDao.getPersonAll();
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
                    personDao.create(person);
                }
                else
                {
                    personDao.update(person);
                }
            }
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

    /*@Override
    public void importPerson(InputStream csvFileStream) throws DaoException, IOException
    {
        try
        {
            BufferedInputStream in = new BufferedInputStream(csvFileStream);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("d://1/test"));
            byte[] buff = new byte[1000];
            int i;
            while((i = in.read(buff)) != -1)
            {
                out.write(buff, 0, i);
            }
            in.close();
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }*/

    @Override
    public void update(Person person) throws DaoException
    {

    }

    @Override
    public List<Person> getPersonAll() throws DaoException
    {
        return personDao.getPersonAll();
    }

    @Override
    public List<Person> sortPerson(String sortValue) throws DaoException
    {
        List<Person> personList = personDao.getPersonAll();
        if(sortValue != null && sortValue.equals("personId"))
        {
            Collections.sort(personList, new Comparator<Person>()
            {
                @Override
                public int compare(Person o1, Person o2)
                {
                    return o1.getPersonId().compareTo(o2.getPersonId());
                }
            });
            return personList;
        }
        if(sortValue != null && sortValue.equals("name"))
        {
            Collections.sort(personList, new Comparator<Person>()
            {
                @Override
                public int compare(Person o1, Person o2)
                {
                    return o1.getPersonName().compareTo(o2.getPersonName());
                }
            });
            return personList;
        }
        if(sortValue != null && sortValue.equals("surname"))
        {
            Collections.sort(personList, new Comparator<Person>()
            {
                @Override
                public int compare(Person o1, Person o2)
                {
                    return o1.getPersonSurname().compareTo(o2.getPersonSurname());
                }
            });
            return personList;
        }
        if(sortValue != null && sortValue.equals("login"))
        {
            Collections.sort(personList, new Comparator<Person>()
            {
                @Override
                public int compare(Person o1, Person o2)
                {
                    return o1.getLogin().compareTo(o2.getLogin());
                }
            });
            return personList;
        }
        if(sortValue != null && sortValue.equals("email"))
        {
            Collections.sort(personList, new Comparator<Person>()
            {
                @Override
                public int compare(Person o1, Person o2)
                {
                    return o1.getEmail().compareTo(o2.getEmail());
                }
            });
            return personList;
        }
        if(sortValue != null && sortValue.equals("phoneNumber"))
        {
            Collections.sort(personList, new Comparator<Person>()
            {
                @Override
                public int compare(Person o1, Person o2)
                {
                    return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
                }
            });
            return personList;
        }

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
