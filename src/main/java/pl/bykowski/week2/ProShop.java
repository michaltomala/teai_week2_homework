package pl.bykowski.week2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Profile("Pro")
@Service
public class ProShop extends PlusShop{

    @Value("${discount}")
    private Integer discount;

    @Override
    public void writeFullPrice() {
        BigDecimal fullPrice = addDiscount(getFullPriceWithVAT()).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(fullPrice);
    }

    private BigDecimal addDiscount(BigDecimal price) {
        BigDecimal discount = BigDecimal.valueOf(1 -  ((double) this.discount/100));
        return price.multiply(discount);
    }

}
