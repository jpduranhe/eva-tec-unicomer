package com.unicomer.prueba.tecnica.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.unicomer.prueba.tecnica.datadake.SalesmanDateFake;
import com.unicomer.prueba.tecnica.entity.Salesman;
import com.unicomer.prueba.tecnica.service.SalesmanService;
import com.unicomer.prueba.tecnica.shared.exception.ControllerException;
import com.unicomer.prueba.tecnica.shared.exception.EntityNotFoundException;
import com.unicomer.prueba.tecnica.shared.exception.ServiceException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SalesmanController.class)
public class SalesmanControllerTest {
    @MockBean
    private SalesmanService salesmanService;
    @Autowired
    MockMvc mockMvc;
    private final ObjectMapper mapper = JsonMapper.builder()
    .addModule(new JavaTimeModule())
    .build();

    private String urlBase = "/api/salesman";


    @DisplayName("Test when Get  Salesman by Id then Fail Service")
    @Test
    public void testWhenGetSalesmanById_thenFailService()throws Exception  {
        when(salesmanService.getSalesmanById(1)).thenThrow(new ServiceException("getby service error"));
        RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
    @DisplayName("Test when Get  Salesman by Id then Not Found")
    @Test
    public void testWhenGetSalesmanById_thenNotFound()throws Exception  {
        when(salesmanService.getSalesmanById(1)).thenThrow(new EntityNotFoundException("getby id not found error"));
         RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @DisplayName("Test when Get  Salesman by Id then Fail Controller")
    @Test
    public void testWhenGetSalesmanById_thenFailController()throws Exception  {
        when(salesmanService.getSalesmanById(1)).thenThrow(new ControllerException("getby controller error"));
        RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }


    @DisplayName("Test when Get  Salesman by Id then CorrectResponse")
    @Test
    public void testWhenGetSalesmanById_thenCorrectResponse()throws Exception  {
        when(salesmanService.getSalesmanById(1)).thenAnswer(i-> SalesmanDateFake.SALESMAN);
        RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
                
    }

    



    @DisplayName("Test when Get All Salesman then Fail Service")
    @Test
    public void testWhenGetAllSalesman_thenFailService()throws Exception  {
        when(salesmanService.getListOfSalesman()).thenThrow(new ServiceException(" service error"));
        RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/all")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
    @DisplayName("Test when Get All Salesman then Fail Controller")
    @Test
    public void testWhenGetAllSalesman_thenFailController()throws Exception  {
        when(salesmanService.getListOfSalesman()).thenThrow(new ControllerException(" controller error"));
        RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/all")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }


    @DisplayName("Test when Get All Salesman then CorrectResponse")
    @Test
    public void testWhenGetAllSalesman_thenCorrectResponse()throws Exception  {
        when(salesmanService.getListOfSalesman()).thenAnswer(i-> SalesmanDateFake.LIST_SALESMAN);
        RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/all")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @DisplayName("Test when Get All Salesman by filter then CorrectResponse")
    @Test
    public void testWhenGetAllSalesmanByFilter_thenCorrectResponse()throws Exception  {
        when(salesmanService.getListOfSalesman(any(Salesman.class))).thenAnswer(i-> SalesmanDateFake.LIST_SALESMAN);
        RequestBuilder request = MockMvcRequestBuilders
                .get(urlBase+"/all?profession=superHero")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
                
    }


    @DisplayName("Test when Post Salesman then CorrectResponse")
    @Test
    public void testWhenPostSalesman_thenCorrectResponse()throws Exception  {
        when(salesmanService.save(any(Salesman.class))).thenAnswer(i-> SalesmanDateFake.SALESMAN);
        
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST);
        RequestBuilder request = MockMvcRequestBuilders
                .post(urlBase)
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));;
    }
    @DisplayName("Test when Post Salesman then Fail Service")
    @Test
    public void testWhenPostSalesman_thenFailService()throws Exception  {

        when(salesmanService.save(any(Salesman.class))).thenThrow(new ServiceException("create service error"));
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST);
        RequestBuilder request = MockMvcRequestBuilders
                .post(urlBase)
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
    @DisplayName("Test when Post Salesman then Fail Controller")
    @Test
    public void testWhenPostSalesman_thenFailController()throws Exception  {
        when(salesmanService.save(any(Salesman.class))).thenThrow(new ControllerException("create controller error"));
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST);
        RequestBuilder request = MockMvcRequestBuilders
                .post(urlBase)
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @DisplayName("Test when Post Salesman then BadRequest")
    @Test
    public void testWhenPostSalesman_thenBadRequest()throws Exception  {
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST.withFirstName(null).withBirthday(null));
        RequestBuilder request = MockMvcRequestBuilders
                .post(urlBase)
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("Test when Put Salesman then CorrectResponse")
    @Test
    public void testWhenPutSalesman_thenCorrectResponse()throws Exception  {
        when(salesmanService.save(any(Salesman.class))).thenAnswer(i-> SalesmanDateFake.SALESMAN);
        
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST);
        RequestBuilder request = MockMvcRequestBuilders
                .put(urlBase+"/1")
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));;
    }


    @DisplayName("Test when Put Salesman then Fail Service")
    @Test
    public void testWhenPutSalesman_thenFailService()throws Exception  {

        when(salesmanService.save(any(Salesman.class))).thenThrow(new ServiceException(" service error"));
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST);
        RequestBuilder request = MockMvcRequestBuilders
                .put(urlBase+"/1")
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
    @DisplayName("Test when Put Salesman then Fail Controller")
    @Test
    public void testWhenPutSalesman_thenFailController()throws Exception  {
        when(salesmanService.save(any(Salesman.class))).thenThrow(new ControllerException(" controller error"));
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST);
        RequestBuilder request = MockMvcRequestBuilders
                .put(urlBase+"/1")
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
    @DisplayName("Test when Put Salesman then not Found")
    @Test
    public void testWhenPutProducto_thenNotFound()throws Exception  {
        when(salesmanService.save(any(Salesman.class))).thenThrow(new EntityNotFoundException(" not found error"));
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST);
        RequestBuilder request = MockMvcRequestBuilders
                .put(urlBase+"/1")
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @DisplayName("Test when Put Salesman then BadRequest")
    @Test
    public void testWhenPutSalesman_thenBadRequest()throws Exception  {
        String salesmanJson = mapper.writeValueAsString(SalesmanDateFake.SALESMAN_REQUEST.withFirstName(null).withBirthday(null));
        RequestBuilder request = MockMvcRequestBuilders
                .put(urlBase+"/1")
                .content(salesmanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}