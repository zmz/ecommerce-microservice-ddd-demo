package com.ecommerce.product.sdk.command.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductNameCommand {

    @NotBlank(message = "产品名字不能为空")
    private String newName;

}
