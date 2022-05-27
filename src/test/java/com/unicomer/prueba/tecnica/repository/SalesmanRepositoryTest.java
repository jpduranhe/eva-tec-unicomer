package com.unicomer.prueba.tecnica.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.unicomer.prueba.tecnica.datadake.SalesmanDateFake;
import com.unicomer.prueba.tecnica.entity.Salesman;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

@SpringBootTest
public class SalesmanRepositoryTest {
    
    @Autowired
    private SalesmanRepository salesmanRepository;



    @DisplayName("when Get Salesman By Id")
    @Test
    @Order(1)
    public void whenGetSalesmanById() {
        Optional<Salesman> result = assertDoesNotThrow(()-> salesmanRepository.findById(1));
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @DisplayName("when Get all Salesman")
    @Test
    @Order(2)
    public void whenGetallSalesman() {
        List<Salesman> result= assertDoesNotThrow(()-> salesmanRepository.findAll());
        assertInstanceOf(List.class, result);
    }

    @DisplayName("when Get all Salesman by filter")
    @Test
    @Order(3)
    public void whenGetallSalesmanByFilter() {

        Salesman filter =Salesman.builder().profession("Superhero").build();
        ExampleMatcher exm = ExampleMatcher.matchingAll()
                        .withIgnorePaths("id","incomes")
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
                Example<Salesman> example = Example.of(filter,exm);
        List<Salesman> result= assertDoesNotThrow(()-> salesmanRepository.findAll(example));
        assertInstanceOf(List.class, result);
        assertEquals(2, result.size());
    }

    @DisplayName("when Save a new Salesman")
    @Test
    @Order(4)
    public void whenSaveNewSalesman() {
        Salesman result= assertDoesNotThrow(()-> salesmanRepository.save(SalesmanDateFake.SALESMAN.withId(0)));
        assertEquals(3, result.getId());
    }

    @DisplayName("when Save exist Salesman")
    @Test
    @Order(5)
    public void whenSaveExistSalesman() {
        Salesman result= assertDoesNotThrow(()-> salesmanRepository.save(SalesmanDateFake.SALESMAN.withId(0)));
        assertEquals(4, result.getId());
        result.setFirstName("Change!!!");
        assertDoesNotThrow(()-> salesmanRepository.save(result));
        Optional<Salesman> resultSaved = assertDoesNotThrow(()-> salesmanRepository.findById(4));
        assertTrue(resultSaved.isPresent());
        assertEquals("Change!!!", resultSaved.get().getFirstName());
    }
    
}