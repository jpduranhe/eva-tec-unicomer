package com.unicomer.prueba.tecnica.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
/**
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-2022
 */
@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salesman implements Serializable {
    
    private static final long serialVersionUID = -3412800489119465802L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String gender;

    private String cellPhone;

    private String homePhone;

    private String addressHome;

    private String profession;

    private int incomes;

}