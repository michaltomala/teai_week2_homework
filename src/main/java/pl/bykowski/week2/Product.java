package pl.bykowski.week2;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;

@Data
public class Product {
    private final String name;
    // BigDecimal instead of Integer
    private final Integer price;

    public boolean isValidated() {
        return StringUtils.isNotEmpty(name) && Objects.nonNull(price);
    }

}
