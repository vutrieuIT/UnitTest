package com.example.unittest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private Long id;
    private String title;
    private String author;
}
