package com.expert.contact.service;


import com.expert.contact.dao.DaoException;
import com.expert.contact.domain.Person;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PersonService
{
    public boolean importPerson(InputStream csvFileStream, String csvSplit) throws DaoException, IOException;
    public List<Person> getPersonAllPaging(Integer offset, Integer recordsQuantity) throws DaoException;
    public List<Person> getPersonAllPagingSort(String sortValue, Integer offset, Integer recordsQuantity) throws DaoException;
    public List<Person> getPersonAll() throws DaoException;
    public Integer getRecordsQuantity();
    public void close() throws DaoException;
}
