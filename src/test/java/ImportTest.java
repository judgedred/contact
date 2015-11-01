import com.expert.contact.dao.DaoException;
import com.expert.contact.dao.DaoFactory;
import com.expert.contact.dao.DaoFactoryImpl;
import com.expert.contact.dao.PersonDao;
import com.expert.contact.domain.Person;
import com.expert.contact.service.PersonService;
import com.expert.contact.service.PersonServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImportTest
{
    @Test
    public void importTest() throws DaoException, IOException
    {
        PersonService personService = new PersonServiceImpl();
        InputStream is = new FileInputStream("csv/test.csv");
        personService.importPerson(is, ",");
        List<Person> personListTest = personService.getPersonAll();
        Assert.assertNotNull(personListTest);
        boolean login1Passed = false;
        boolean login2Passed = false;
        boolean login4Passed = false;

        for(Person p : personListTest)
        {
            if(p.getLogin().equals("login1"))
            {
                Assert.assertTrue(p.getPersonName().equals("Name1_updated"));
                Assert.assertTrue(p.getPersonSurname().equals("Surname1_updated"));
                Assert.assertTrue(p.getEmail().equals("email1_updated"));
                Assert.assertTrue(p.getPhoneNumber().equals(3L));
                login1Passed = true;
            }
            else if(p.getLogin().equals("login2"))
            {
                Assert.assertTrue(p.getPersonName().equals("Name2"));
                Assert.assertTrue(p.getPersonSurname().equals("Surname2"));
                Assert.assertTrue(p.getEmail().equals("email2"));
                Assert.assertTrue(p.getPhoneNumber().equals(2L));
                login2Passed = true;
            }
            else if(p.getLogin().equals("login4"))
            {
                Assert.assertTrue(p.getPersonName().equals("Name4"));
                Assert.assertTrue(p.getPersonSurname().equals("Surname4"));
                Assert.assertTrue(p.getEmail().equals("email4"));
                Assert.assertTrue(p.getPhoneNumber().equals(4L));
                login4Passed = true;
            }
        }
        if(!login1Passed || !login2Passed || !login4Passed)
        {
            Assert.fail("Import failed. Logins in csv and logins in database don't match.");

        }
    }

    @After
    public void cleanUp() throws DaoException
    {
        DaoFactory daoFactory = new DaoFactoryImpl();
        PersonDao personDao = daoFactory.getPersonDao();
        List<Person> personList = personDao.getPersonAll();
        for(Person p : personList)
        {
            if(p.getLogin().equals("login1") || p.getLogin().equals("login2")
                    || p.getLogin().equals("login4"))
            {
                personDao.delete(p);
            }
        }
    }
}
