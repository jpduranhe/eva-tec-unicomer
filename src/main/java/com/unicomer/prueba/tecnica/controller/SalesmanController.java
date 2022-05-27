package com.unicomer.prueba.tecnica.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.unicomer.prueba.tecnica.dto.SalesmanDtoRequest;
import com.unicomer.prueba.tecnica.dto.SalesmanDtoResponse;
import com.unicomer.prueba.tecnica.entity.Salesman;
import com.unicomer.prueba.tecnica.mapper.SalesmanMapper;
import com.unicomer.prueba.tecnica.service.SalesmanService;
import com.unicomer.prueba.tecnica.shared.dto.ErrorMessageResponse;
import com.unicomer.prueba.tecnica.shared.exception.ControllerException;
import com.unicomer.prueba.tecnica.shared.exception.EntityNotFoundException;
import com.unicomer.prueba.tecnica.shared.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;

@Api(tags = "Salesman")
@RestController
@RequestMapping("/api/salesman")
@CrossOrigin("*")
@CommonsLog
public class SalesmanController {
    
    @Autowired
    SalesmanService salesmanService;

    @ApiOperation(notes = "Return a Saleman by Id ",
            value = "Return a Saleman by Id",
            nickname ="getSalesmanById")
    @ApiResponses(value = {
            @ApiResponse(code=200, response = SalesmanDtoResponse.class,message = "Success"),
            @ApiResponse(code=500, response = ErrorMessageResponse.class,message = "Internal Error"),
            @ApiResponse(code=404, response = ErrorMessageResponse.class,message = "Not Found Salesman")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SalesmanDtoResponse> getSalesmanById(@PathVariable int id) {
        try {
            Salesman salesmanSeek = salesmanService.getSalesmanById(id);
            SalesmanDtoResponse response= SalesmanMapper.toResponse(salesmanSeek);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (EntityNotFoundException e){throw e;}
        catch (ServiceException e){throw e;}
        catch (Exception e){
            log.error(e);
            throw new ControllerException(e.getMessage());
        }
    }
    @ApiOperation(notes = "Return a List of all Salesman register  ",
            value = "Return a List of all Salesman register",
            nickname ="getAllSalesman")
    @ApiResponses(value = {
            @ApiResponse(code=200, response = SalesmanDtoResponse.class,message = "Success"),
            @ApiResponse(code=500, response = ErrorMessageResponse.class,message = "Internal Error"),
            @ApiResponse(code=404, response = ErrorMessageResponse.class,message = "Not Found Salesman")
    })
    @GetMapping("/all")
    public ResponseEntity<List<SalesmanDtoResponse>> getAllSalesman(@RequestParam(required = false) Optional<String> profession) {
        try {
            List<Salesman> listSalesman =  (profession.isPresent()) ? salesmanService.getListOfSalesman(Salesman.builder().profession(profession.get()).build()): salesmanService.getListOfSalesman();
            List<SalesmanDtoResponse> response= SalesmanMapper.toListResponse(listSalesman);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (EntityNotFoundException e){throw e;}
        catch (ServiceException e){throw e;}
        catch (Exception e){
            log.error(e);
            throw new ControllerException(e.getMessage());
        }
    }

    @ApiOperation(notes = "Save a new Salesman",
            value = "Return Salesman saved",
            nickname ="postSalesman")
    @ApiResponses(value = {
            @ApiResponse(code=200, response = SalesmanDtoResponse.class,message = "Success"),
            @ApiResponse(code=500, response = ErrorMessageResponse.class,message = "Internal Error")
    })
    @PostMapping
    public ResponseEntity<SalesmanDtoResponse> postSalesman(@Valid @RequestBody SalesmanDtoRequest salesmanDtoRequest) {
        try {
            Salesman salesmanForSave = SalesmanMapper.toEntity(salesmanDtoRequest);
            Salesman salesmantoSaved = salesmanService.save(salesmanForSave);
            SalesmanDtoResponse response= SalesmanMapper.toResponse(salesmantoSaved);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ServiceException e){throw e;}
        catch (Exception e){
            log.error(e);
            throw new ControllerException(e.getMessage());
        }
    }

    @ApiOperation(notes = "Edit a exist Salesman",
            value = "Return Salesman saved",
            nickname ="putProducto")
    @ApiResponses(value = {
            @ApiResponse(code=200, response = SalesmanDtoResponse.class,message = "Success"),
            @ApiResponse(code=500, response = ErrorMessageResponse.class,message = "Internal Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SalesmanDtoResponse> putProducto(@PathVariable int id, @Valid @RequestBody SalesmanDtoRequest salesmanDtoRequest) {
        try {
            Salesman productoForSave = SalesmanMapper.toEntity(salesmanDtoRequest);
            Salesman productotoSaved = salesmanService.save(productoForSave.withId(id));
            SalesmanDtoResponse salida= SalesmanMapper.toResponse(productotoSaved);

            return new ResponseEntity<>(salida, HttpStatus.OK);
        }catch (EntityNotFoundException e){throw e;}
        catch (ServiceException e){throw e;}
        catch (Exception e){
            log.error(e);
            throw new ControllerException(e.getMessage());
        }
    }
}