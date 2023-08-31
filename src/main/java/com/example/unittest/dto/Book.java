package com.example.unittest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private String title;
    private String author;
}
