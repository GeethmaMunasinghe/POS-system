package com.example.project01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDTO {
    private String itemCode;
    private String description;
    private int qtyOnHand;
    private double unitPrice;

}
