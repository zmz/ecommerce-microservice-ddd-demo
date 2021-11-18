package com.ecommerce.order.order.apitest.about;

import org.junit.jupiter.api.Test;

import com.ecommerce.order.order.apitest.BaseApiTest;

import static org.hamcrest.Matchers.containsString;

class AboutControllerApiTest extends BaseApiTest {

    @Test
    public void should_display_about_info() {
        given()
                .when()
                .get("/about")
                .then()
                .statusCode(200)
                .body("deployTime", containsString("Asia/Shanghai"));
    }


}