package com.ecommerce.product.sdk.representation.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithCategoryRepresentation {
    private String id;
    private String name;
    private String categoryId;
    private String categoryName;
}
