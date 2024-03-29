package com.ecommerce.shared.model;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {
    private String province;
    private String city;
    private String detail;

    private Address(String province, String city, String detail) {
        this.province = province;
        this.city = city;
        this.detail = detail;
    }

    public static Address of(String province, String city, String detail) {
        return new Address(province, city, detail);
    }

    public Address changeDetailTo(String detail) {
        return new Address(this.province, this.city, detail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return province.equals(address.province) &&
                city.equals(address.city) &&
                detail.equals(address.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, detail);
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDetail() {
        return detail;
    }
}
