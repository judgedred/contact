package com.expert.contact.dao;

import com.expert.contact.domain.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PersonDaoImpl implements PersonDao
{
    private Connection connection;
    private PreparedStatement pstmtCreate = null;
    private PreparedStatement pstmtUpdate = null;
    private PreparedStatement pstmtGetAll = null;
    private PreparedStatement pstmtLastId = null;
    private PreparedStatement pstmtGetById = null;
    private PreparedStatement pstmtDelete = null;
    private ResultSet rs = null;
    private static final String sqlCreate =	"Insert Into Person(person_name, person_surname, login, email, phone_number) Values(?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "Update Person Set person_name = ?, person_surname = ?, login = ?, email = ?, phone_number = ? Where person_id = ?";
    private static final String sqlGetAll = "Select person_id, person_name, person_surname, login, email, phone_number From Person";
    private static final String sqlLastId = "Select person_id, person_name, person_surname, login, email, phone_number From Person Where person_id = last_insert_id()";
    private static final String sqlGetById = "Select person_id, person_name, person_surname, login, email, phone_number From Person Where person_id = ?";
    private static final String sqlDelete = "Delete From Person Where person_id = ?";

    private PreparedStatement getPstmtCreate() throws DaoException
    {
        try
        {
            if(pstmtCreate == null)
            {
                return pstmtCreate = connection.prepareStatement(sqlCreate);
            }
            else
            {
                return pstmtCreate;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    private PreparedStatement getPstmtUpdate() throws DaoException
    {
        try
        {
            if(pstmtUpdate == null)
            {
                return pstmtUpdate = connection.prepareStatement(sqlUpdate);
            }
            else
            {
                return pstmtUpdate;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    private PreparedStatement getPstmtGetAll() throws DaoException
    {
        try
        {
            if(pstmtGetAll == null)
            {
                return pstmtGetAll = connection.prepareStatement(sqlGetAll);
            }
            else
            {
                return pstmtGetAll;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    private PreparedStatement getPstmtLastId() throws DaoException
    {
        try
        {
            if(pstmtLastId == null)
            {
                return pstmtLastId = connection.prepareStatement(sqlLastId);
            }
            else
            {
                return pstmtLastId;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    private PreparedStatement getPstmtGetById() throws DaoException
    {
        try
        {
            if(pstmtGetById == null)
            {
                return pstmtGetById = connection.prepareStatement(sqlGetById);
            }
            else
            {
                return pstmtGetById;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    private PreparedStatement getPstmtDelete() throws DaoException
    {
        try
        {
            if(pstmtDelete == null)
            {
                return pstmtDelete = connection.prepareStatement(sqlDelete);
            }
            else
            {
                return pstmtDelete;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }


    @Override
    public Person create(Person person) throws DaoException
    {
        try
        {
            pstmtCreate = getPstmtCreate();
            pstmtCreate.setString(1, person.getPersonName());
            pstmtCreate.setString(2, person.getPersonSurname());
            pstmtCreate.setString(3, person.getLogin());
            pstmtCreate.setString(4, person.getEmail());
            pstmtCreate.setLong(5, person.getPhoneNumber());
            pstmtCreate.executeUpdate();
            pstmtLastId = getPstmtLastId();
            rs = pstmtLastId.executeQuery();
            if(rs.next())
            {
                person.setPersonId(rs.getInt(1));
                person.setPersonName(rs.getString(2));
                person.setPersonSurname(rs.getString(3));
                person.setLogin(rs.getString(4));
                person.setEmail(rs.getString(5));
                person.setPhoneNumber(rs.getLong(6));
                return person;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Person person) throws DaoException
    {
        try
        {
            pstmtUpdate = getPstmtUpdate();
            pstmtUpdate.setString(1, person.getPersonName());
            pstmtUpdate.setString(2, person.getPersonSurname());
            pstmtUpdate.setString(3, person.getLogin());
            pstmtUpdate.setString(4, person.getEmail());
            pstmtUpdate.setLong(5, person.getPhoneNumber());
            pstmtUpdate.setInt(6, person.getPersonId());
            pstmtUpdate.executeUpdate();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Person> getPersonAll() throws DaoException
    {
        List<Person> personList = new ArrayList<>();
        try
        {
            pstmtGetAll = getPstmtGetAll();
            rs = pstmtGetAll.executeQuery();
            while(rs.next())
            {
                Person person = new Person();
                person.setPersonId(rs.getInt(1));
                person.setPersonName(rs.getString(2));
                person.setPersonSurname(rs.getString(3));
                person.setLogin(rs.getString(4));
                person.setEmail(rs.getString(5));
                person.setPhoneNumber(rs.getLong(6));
                personList.add(person);
            }
            return personList;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public Person getPersonById(int id) throws DaoException
    {
        try
        {
            Person person = new Person();
            pstmtGetById = getPstmtGetById();
            pstmtGetById.setInt(1, id);
            rs = pstmtGetById.executeQuery();
            if(rs.next())
            {
                person.setPersonId(rs.getInt(1));
                person.setPersonName(rs.getString(2));
                person.setPersonSurname(rs.getString(3));
                person.setLogin(rs.getString(4));
                person.setEmail(rs.getString(5));
                person.setPhoneNumber(rs.getLong(6));
                return person;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Person person) throws DaoException
    {
        try
        {
            pstmtDelete = getPstmtDelete();
            pstmtDelete.setInt(1, person.getPersonId());
            pstmtDelete.executeUpdate();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException
    {
        try
        {
            if(pstmtCreate != null)
            {
                pstmtCreate.close();
            }
            if(pstmtUpdate != null)
            {
                pstmtUpdate.close();
            }
            if(pstmtDelete != null)
            {
                pstmtDelete.close();
            }
            if(pstmtGetAll != null)
            {
                pstmtGetAll.close();
            }
            if(pstmtGetById != null)
            {
                pstmtGetById.close();
            }
            if(pstmtLastId != null)
            {
                pstmtLastId.close();
            }
            connection.close();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public PersonDaoImpl() throws DaoException
    {
        try
        {
            connection = DaoFactoryImpl.getConnection();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }
}
