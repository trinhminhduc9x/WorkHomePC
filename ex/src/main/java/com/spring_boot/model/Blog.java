package com.spring_boot.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String content;

    private String note;


    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;



}