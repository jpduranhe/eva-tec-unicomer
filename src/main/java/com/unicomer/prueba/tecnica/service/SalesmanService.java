package com.unicomer.prueba.tecnica.service;

import java.util.List;


import com.unicomer.prueba.tecnica.entity.Salesman;
import com.unicomer.prueba.tecnica.shared.exception.EntityNotFoundException;
import com.unicomer.prueba.tecnica.shared.exception.ServiceException;
/**
 * Interface that defines the seller's service methods
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-2022
 */
public interface SalesmanService {
    
    /**
     * this method find a salesman by id
     * @param id
     * @return
     * @throws EntityNotFoundException
     * @throws ServiceException
     */
    public Salesman getSalesmanById(int id) throws EntityNotFoundException, ServiceException;


    /**
     * this method get a list all salesman
     * @return
     * @throws ServiceException
     */
    public List<Salesman> getListOfSalesman() throws ServiceException;

    /**
     * this method gets a list of all salesman by filter
     * @param filter
     * @return
     * @throws ServiceException
     */
    public List<Salesman> getListOfSalesman(Salesman filter) throws ServiceException;


    /**
     * this method save or update a new salesmann
     * @param salesman
     * @return
     * @throws ServiceException
     */
    public Salesman save(Salesman salesman) throws EntityNotFoundException,ServiceException;

    
}