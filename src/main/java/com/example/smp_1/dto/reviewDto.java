package com.example.smp_1.dto;

import lombok.Data;

@Data
public class reviewDto {
    private String reTitle, reWriter, reContent, reCreate, reUpdate, reYn, reShop;
    private int reLike;
}
