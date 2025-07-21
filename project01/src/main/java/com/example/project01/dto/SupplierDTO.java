package com.example.project01.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SupplierDTO {
    private String id;
    private String name;
    private String address;
    private int tel;


}
