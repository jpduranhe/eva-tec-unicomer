package com.unicomer.prueba.tecnica.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.unicomer.prueba.tecnica.datadake.SalesmanDateFake;
import com.unicomer.prueba.tecnica.entity.Salesman;
import com.unicomer.prueba.tecnica.repository.SalesmanRepository;
import com.unicomer.prueba.tecnica.shared.exception.EntityNotFoundException;
import com.unicomer.prueba.tecnica.shared.exception.ServiceException;

import org.hibernate.exception.SQLGrammarException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;

@SpringBootTest
public class SalesmanServiceTest {
    @MockBean
    private SalesmanRepository salesmanRepository;

    @Autowired
    private SalesmanService salesmanService;


    @DisplayName("when Get Salesman By Id Success")
    @Test
    public void whenGetSalesmanByIdSuccess() {
        when(salesmanRepository.findById(anyInt())).thenReturn(Optional.of(SalesmanDateFake.SALESMAN));
        assertDoesNotThrow(()-> salesmanService.getSalesmanById(1));
    }
    @DisplayName("when Get Salesman By Id Not Found")
    @Test
    public void whenGetSalesmanByIdNotFound() {
        when(salesmanRepository.findById(anyInt())).thenThrow(new EntityNotFoundException("Not found Salesman"));
        assertThrows(EntityNotFoundException.class,()-> salesmanService.getSalesmanById(10));
    }

    @DisplayName("when Get Salesman By Id Fail")
    @Test
    public void whenGetSalesmanByIdFail() {
        when(salesmanRepository.findById(anyInt())).thenThrow(new SQLGrammarException(null,null));
        assertThrows(ServiceException.class,()->salesmanService.getSalesmanById(1));
    }

    @DisplayName("when Get All Salesman Success")
    @Test
    public void whenGetAllSalesmanSuccess() {
        when(salesmanRepository.findAll()).thenReturn(SalesmanDateFake.LIST_SALESMAN);
        assertDoesNotThrow(()-> salesmanService.getListOfSalesman());
    }

    @DisplayName("when Get All Salesman Fail")
    @Test
    public void whengetAllSalesmanFail() {
        when(salesmanRepository.findAll()).thenThrow(new SQLGrammarException(null,null));
        assertThrows(ServiceException.class,()->salesmanService.getListOfSalesman());
    }

    @DisplayName("when Get All Salesman by filter Success")
    @Test
    public void whenGetAllSalesmanByFilterSuccess() {
        when(salesmanRepository.findAll(any(Example.class))).thenReturn(SalesmanDateFake.LIST_SALESMAN);
        assertDoesNotThrow(()-> salesmanService.getListOfSalesman(SalesmanDateFake.SALESMAN));
    }

    @DisplayName("when Get All Salesman by filter Fail")
    @Test
    public void whengetAllSalesmanByFilterFail() {
        
        when(salesmanRepository.findAll(any(Example.class))).thenThrow(new SQLGrammarException(null,null));
        assertThrows(ServiceException.class,()->salesmanService.getListOfSalesman(SalesmanDateFake.SALESMAN));
    }

    @DisplayName("when Save New Salesman Success")
    @Test
    public void whenSaveNewSalesmanSuccess() {
        when(salesmanRepository.save(any(Salesman.class))).thenReturn(SalesmanDateFake.SALESMAN);
        assertDoesNotThrow(()-> salesmanService.save(SalesmanDateFake.SALESMAN.withId(0)));
    }
    

    @DisplayName("when Save New Salesman Fail")
    @Test
    public void whenSaveNewSalesmanFail() {
        when(salesmanRepository.save(any(Salesman.class))).thenThrow(new SQLGrammarException(null,null));
        assertThrows(ServiceException.class,()->salesmanService.save(SalesmanDateFake.SALESMAN.withId(0)));
    }

    @DisplayName("when Save Exist Salesman Success")
    @Test
    public void whenSaveExistSalesmanSuccess() {
        when(salesmanRepository.findById(anyInt())).thenReturn(Optional.of(SalesmanDateFake.SALESMAN));
        when(salesmanRepository.save(any(Salesman.class))).thenReturn(SalesmanDateFake.SALESMAN);
        assertDoesNotThrow(()-> salesmanService.save(SalesmanDateFake.SALESMAN));
    }
    @DisplayName("when Save Exist Salesman Not Found")
    @Test
    public void whenSaveExistSalesmanNotFound() {
        when(salesmanRepository.findById(anyInt())).thenThrow(new EntityNotFoundException("Not found Salesman"));
        assertThrows(EntityNotFoundException.class,()-> salesmanService.save(SalesmanDateFake.SALESMAN));
    }

    @DisplayName("when Save Exist Salesman Fail")
    @Test
    public void whenSaveExistSalesmanFail() {
        when(salesmanRepository.findById(anyInt())).thenReturn(Optional.of(SalesmanDateFake.SALESMAN));
        when(salesmanRepository.save(any(Salesman.class))).thenThrow(new SQLGrammarException(null,null));
        assertThrows(ServiceException.class,()->salesmanService.save(SalesmanDateFake.SALESMAN));
    }

    


    
}