package com.ecommerce.inventory.sdk.command.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncreaseInventoryCommand {
    @Min(1)
    private int increaseNumber;
}
