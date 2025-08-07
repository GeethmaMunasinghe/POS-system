package com.example.project01.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SupplierDTO {
    private String id;
    private String name;
    private String address;
    private String telNo;


}
