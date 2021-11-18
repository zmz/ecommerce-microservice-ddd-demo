package com.ecommerce.order.sdk.command.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAddressDetailCommand {
    @NotNull(message = "详细地址不能为空")
    private String detail;

}
