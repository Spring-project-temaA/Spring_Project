package com.example.smp_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class reviewDto {
    public String reTitle;
    public String reWriter;
    public String reContent;
    public String reCreate;
    public String reUpdate;
    public String reLike;
    public String reShop;
    public String reYn;
    public String reIdx;

}
