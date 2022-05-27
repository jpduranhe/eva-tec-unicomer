package com.unicomer.prueba.tecnica.service.impl;

import java.util.List;


import com.unicomer.prueba.tecnica.entity.Salesman;
import com.unicomer.prueba.tecnica.repository.SalesmanRepository;
import com.unicomer.prueba.tecnica.service.SalesmanService;
import com.unicomer.prueba.tecnica.shared.exception.EntityNotFoundException;
import com.unicomer.prueba.tecnica.shared.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.extern.apachecommons.CommonsLog;
/**
 * Implementatio of service that defines the salesman service methods
 * 
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-2022
 */
@Service
@CommonsLog
public class SalesmanServiceImpl implements SalesmanService {

    @Autowired
    SalesmanRepository salesmanRepository;

    @Override
    public List<Salesman> getListOfSalesman() throws ServiceException {
        try {
            
            return salesmanRepository.findAll();
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException("Error of process",ex);
        }
    }

    @Override
    public List<Salesman> getListOfSalesman(Salesman filter) throws ServiceException {
        try {
            Assert.notNull(filter, "The filter cannot by null");

            ExampleMatcher exm = ExampleMatcher.matchingAll()
                        .withIgnorePaths("id","incomes")
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
                Example<Salesman> example = Example.of(filter,exm);
            return salesmanRepository.findAll(example);
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException("Error of process",ex);
        }
    }

    @Override
    public Salesman getSalesmanById(int id) throws EntityNotFoundException, ServiceException {
        try {
            Salesman salesman = salesmanRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Salesman not found"));

            return salesman;
        }
        catch (EntityNotFoundException ex) { throw ex;}
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException("Error of process",ex);
        }
    }

    @Override
    public Salesman save(Salesman salesman) throws EntityNotFoundException, ServiceException {
        try {

            if(salesman.getId() == 0){
                return salesmanRepository.save(salesman);
            } else {
                Salesman salesmanSaved = salesmanRepository.findById(salesman.getId())
                .orElseThrow(() -> new EntityNotFoundException("Salesman not found"));

                Salesman salesmanToSave = salesmanSaved.withAddressHome(salesman.getAddressHome())
                .withBirthday(salesman.getBirthday())
                .withCellPhone(salesman.getCellPhone())
                .withFirstName(salesman.getFirstName())
                .withGender(salesman.getGender())
                .withHomePhone(salesman.getHomePhone())
                .withIncomes(salesman.getIncomes())
                .withLastName(salesman.getLastName())
                .withProfession(salesman.getProfession());
                return salesmanRepository.save(salesmanToSave);

            }
        }
        catch (EntityNotFoundException ex) { throw ex;}
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException("Error of process",ex);
        }
    }
    
}