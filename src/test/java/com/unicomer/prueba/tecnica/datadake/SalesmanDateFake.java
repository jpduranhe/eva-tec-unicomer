package com.unicomer.prueba.tecnica.datadake;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.unicomer.prueba.tecnica.dto.SalesmanDtoRequest;
import com.unicomer.prueba.tecnica.entity.Salesman;

public class SalesmanDateFake {
    
    public static Salesman SALESMAN = Salesman.builder()
    .id(2)
    .firstName("Bruce")
    .lastName("Wayne")
    .birthday( LocalDate.now())
    .gender("male")
    .cellPhone("9999999")
    .homePhone("555555555")
    .addressHome("One Avenue 123")
    .profession("SuperHero")
    .incomes(100000)
    .build();

    public static SalesmanDtoRequest SALESMAN_REQUEST = SalesmanDtoRequest.builder()
    .firstName("Bruce")
    .lastName("Wayne")
    .birthday(LocalDate.now())
    .gender("male")
    .cellPhone("9999999")
    .homePhone("555555555")
    .addressHome("One Avenue 123")
    .profession("SuperHero")
    .incomes(100000)
    .build();


    public static List<Salesman> LIST_SALESMAN = Arrays.asList(SALESMAN,SALESMAN);
}