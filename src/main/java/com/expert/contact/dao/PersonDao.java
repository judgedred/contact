package com.expert.contact.dao;


import com.expert.contact.domain.Person;
import java.util.List;

public interface PersonDao
{
    public Person runBatch() throws DaoException;
    public void createAddBatch(Person person) throws DaoException;
    public void updateAddBatch(Person person) throws DaoException;
    public List<Person> getPersonAllPaging(Integer offset, Integer records) throws DaoException;
    public List<Person> getPersonAllPagingSort(String sortValue, Integer offset, Integer recordsQuantity) throws DaoException;
    public List<Person> getPersonAll() throws DaoException;
    public int getRecordsQuantity();
    public Person getPersonById(int id) throws DaoException;
    public void delete(Person person) throws DaoException;
    public void close() throws DaoException;
}
