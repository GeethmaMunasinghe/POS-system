package com.example.project01.model;

import com.example.project01.dto.TransactionDTO;

import java.util.ArrayList;
import java.util.List;

public class TransactionModel {
    private static final List<TransactionDTO> itemList = new ArrayList<>();

    static {
        // Sample items
        itemList.add(new TransactionDTO("I001", "Mouse", 100, 1500.00));
        itemList.add(new TransactionDTO("I002", "Keyboard", 75, 2500.00));
        itemList.add(new TransactionDTO("I003", "Monitor", 50, 30000.00));
    }

    public List<TransactionDTO> getAllItems() {
        return itemList;
    }

    public TransactionDTO findItemById(String id) {
        for (TransactionDTO item : itemList) {
            if (item.getItemCode().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
