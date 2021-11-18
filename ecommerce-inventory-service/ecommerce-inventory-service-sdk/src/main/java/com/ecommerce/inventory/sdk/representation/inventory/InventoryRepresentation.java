package com.ecommerce.inventory.sdk.representation.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRepresentation {
    private String id;
    private String productName;
    private int remains;
}
