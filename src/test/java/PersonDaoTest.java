import com.expert.contact.dao.DaoException;
import com.expert.contact.dao.DaoFactory;
import com.expert.contact.dao.DaoFactoryImpl;
import com.expert.contact.dao.PersonDao;
import com.expert.contact.domain.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PersonDaoTest
{
    @Test
    public void testGetPersonById() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        Person personTest = personDao.getPersonById(1);
        Assert.assertNotNull(personTest);
    }

    @Test
    public void testCreate() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        Person person = new Person();
        person.setPersonName("testCreatePassed");
        person.setPersonSurname("testCreatePassed");
        person.setLogin("testCreatePassed");
        person.setEmail("test@gmail.com");
        person.setPhoneNumber((long) 5553735);
        String personNameExpected = person.getPersonName();
        String personSurnameExpected = person.getPersonSurname();
        String loginExpected = person.getLogin();
        String emailExpected = person.getEmail();
        Long phoneNumberExpected = person.getPhoneNumber();
        Person personTest = personDao.create(person);
        Assert.assertNotNull(personTest);
        String personNameResult = personTest.getPersonName();
        String personSurnameResult = personTest.getPersonSurname();
        String loginResult = personTest.getLogin();
        String emailResult = personTest.getEmail();
        Long phoneNumberResult = personTest.getPhoneNumber();
        Assert.assertEquals(personNameExpected, personNameResult);
        Assert.assertEquals(personSurnameExpected, personSurnameResult);
        Assert.assertEquals(loginExpected, loginResult);
        Assert.assertEquals(emailExpected, emailResult);
        Assert.assertEquals(phoneNumberExpected, phoneNumberResult);
    }

    @Test
    public void testUpdate() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        Person person = new Person();
        person.setPersonId(2);
        person.setPersonName("testUpdatePassed");
        person.setPersonSurname("testUpdatePassed");
        person.setLogin("testUpdatePassed");
        person.setEmail("test@gmail.com");
        person.setPhoneNumber((long) 5553737);
        String personNameExpected = person.getPersonName();
        String personSurnameExpected = person.getPersonSurname();
        String loginExpected = person.getLogin();
        String emailExpected = person.getEmail();
        Long phoneNumberExpected = person.getPhoneNumber();
        personDao.update(person);
        Person personTest = personDao.getPersonById(person.getPersonId());
        Assert.assertNotNull(personTest);
        String personNameResult = personTest.getPersonName();
        String personSurnameResult = personTest.getPersonSurname();
        String loginResult = personTest.getLogin();
        String emailResult = personTest.getEmail();
        Long phoneNumberResult = personTest.getPhoneNumber();
        Assert.assertEquals(personNameExpected, personNameResult);
        Assert.assertEquals(personSurnameExpected, personSurnameResult);
        Assert.assertEquals(loginExpected, loginResult);
        Assert.assertEquals(emailExpected, emailResult);
        Assert.assertEquals(phoneNumberExpected, phoneNumberResult);
    }

    @Test
    public void testGetPersonAll() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        List<Person> listTest = personDao.getPersonAll();
        Assert.assertNotNull(listTest);
        Assert.assertTrue(listTest.size() > 0);
    }

    @Test
    public void testDelete() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        Person person = personDao.getPersonById(3);
        personDao.delete(person);
        Assert.assertNull(personDao.getPersonById(3));
    }

    @After
    public void cleanUp() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        List<Person> personList = personDao.getPersonAll();
        for(Person p : personList)
        {
            if(p.getPersonName().equals("testCreatePassed") || p.getPersonName().equals("testUpdatePassed"))
            {
                personDao.delete(p);
            }
        }
    }
}
