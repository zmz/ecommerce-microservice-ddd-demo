/*****************************************************************************
 * Copyright (c) Huawei Technologies Co., Ltd. 2015-2019. All rights reserved.
 *
 *****************************************************************************
 Description: 添加描述
 Author: z00438188
 Created: 2021-11-16
 ****************************************************************************/

package com.ecommerce;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/********************************************************************
 * 一句话功能简述
 * 功能详细描述
 *
 * @author z00438188
 * @version V1.0
 * @since 2021-11-16
 ********************************************************************/
@SpringBootApplication
public class EcommerceOrderQueryServiceAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceOrderQueryServiceAPIApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
