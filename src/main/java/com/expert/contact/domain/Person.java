package com.expert.contact.domain;


public class Person
{
    private Integer personId;
    private String personName;
    private String personSurname;
    private String login;
    private String email;
    private Long phoneNumber;

    public Integer getPersonId()
    {
        return personId;
    }

    public void setPersonId(Integer personId)
    {
        this.personId = personId;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public String getPersonSurname()
    {
        return personSurname;
    }

    public void setPersonSurname(String personSurname)
    {
        this.personSurname = personSurname;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Long getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        Person person = (Person) o;

        if(!personId.equals(person.personId))
        {
            return false;
        }
        if(!personName.equals(person.personName))
        {
            return false;
        }
        if(!personSurname.equals(person.personSurname))
        {
            return false;
        }
        if(!login.equals(person.login))
        {
            return false;
        }
        if(!email.equals(person.email))
        {
            return false;
        }
        return phoneNumber.equals(person.phoneNumber);

    }

    @Override
    public int hashCode()
    {
        return personId.hashCode();
    }
}
