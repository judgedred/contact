import com.expert.contact.dao.DaoException;
import com.expert.contact.dao.DaoFactory;
import com.expert.contact.dao.DaoFactoryImpl;
import com.expert.contact.dao.PersonDao;
import com.expert.contact.domain.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
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
        personDao.createAddBatch(person);
        Person personTest = personDao.runBatch();
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
        personDao.updateAddBatch(person);
        personDao.runBatch();
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

    @Test
    public void testGetPersonAllPaging() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        List<Person> listTest = personDao.getPersonAllPaging(0, 3);
        Assert.assertNotNull(listTest);
        Assert.assertTrue(listTest.size() == 3);
    }

    @Test
    public void testGetPersonAllPagingSort() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        int offset = 0;
        int recordsQuantity = 3;
        List<Person> personList = personDao.getPersonAll();
        Assert.assertNotNull(personList);

        String sortValue = "personId";
        Collections.sort(personList, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getPersonId().compareTo(o2.getPersonId());
            }
        });
        List<Person> personListTest = personDao.getPersonAllPagingSort(sortValue, offset, recordsQuantity);
        Assert.assertEquals(personList.get(0), personListTest.get(0));
        Assert.assertEquals(personList.get(1), personListTest.get(1));
        Assert.assertEquals(personList.get(2), personListTest.get(2));

        sortValue = "name";
        Collections.sort(personList, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getPersonName().compareTo(o2.getPersonName());
            }
        });
        personListTest = personDao.getPersonAllPagingSort(sortValue, offset, recordsQuantity);
        Assert.assertEquals(personList.get(0), personListTest.get(0));
        Assert.assertEquals(personList.get(1), personListTest.get(1));
        Assert.assertEquals(personList.get(2), personListTest.get(2));

        sortValue = "surname";
        Collections.sort(personList, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getPersonSurname().compareTo(o2.getPersonSurname());
            }
        });
        personListTest = personDao.getPersonAllPagingSort(sortValue, offset, recordsQuantity);
        Assert.assertEquals(personList.get(0), personListTest.get(0));
        Assert.assertEquals(personList.get(1), personListTest.get(1));
        Assert.assertEquals(personList.get(2), personListTest.get(2));

        sortValue = "login";
        Collections.sort(personList, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getLogin().compareTo(o2.getLogin());
            }
        });
        personListTest = personDao.getPersonAllPagingSort(sortValue, offset, recordsQuantity);
        Assert.assertEquals(personList.get(0), personListTest.get(0));
        Assert.assertEquals(personList.get(1), personListTest.get(1));
        Assert.assertEquals(personList.get(2), personListTest.get(2));

        sortValue = "email";
        Collections.sort(personList, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getEmail().compareTo(o2.getEmail());
            }
        });
        personListTest = personDao.getPersonAllPagingSort(sortValue, offset, recordsQuantity);
        Assert.assertEquals(personList.get(0), personListTest.get(0));
        Assert.assertEquals(personList.get(1), personListTest.get(1));
        Assert.assertEquals(personList.get(2), personListTest.get(2));

        sortValue = "phoneNumber";
        Collections.sort(personList, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
            }
        });
        personListTest = personDao.getPersonAllPagingSort(sortValue, offset, recordsQuantity);
        Assert.assertEquals(personList.get(0), personListTest.get(0));
        Assert.assertEquals(personList.get(1), personListTest.get(1));
        Assert.assertEquals(personList.get(2), personListTest.get(2));
    }

    @AfterClass
    public static void cleanUp() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        List<Person> personList = personDao.getPersonAll();
        for(Person p : personList)
        {
            if(p.getPersonName().equals("testCreatePassed") || p.getPersonName().equals("testUpdatePassed")
                    || p.getPersonName().equals("TestPaging1") || p.getPersonName().equals("TestPaging2")
                    || p.getPersonName().equals("TestPaging3"))
            {
                personDao.delete(p);
            }
        }
    }
}
