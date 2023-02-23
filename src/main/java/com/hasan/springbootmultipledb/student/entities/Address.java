package com.hasan.springbootmultipledb.student.entities;

import javax.persistence.*;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String village;
    private String postOffice;
}
