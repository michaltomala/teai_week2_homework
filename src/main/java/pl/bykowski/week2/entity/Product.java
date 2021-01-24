package pl.bykowski.week2.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class Product {
    private final String name;
    private final BigDecimal price;

    public boolean isValidated() {
        return StringUtils.isNotEmpty(name) && Objects.nonNull(price);
    }

}
