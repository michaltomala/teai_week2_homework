package pl.bykowski.week2.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Profile("Pro")
@Service
public class ProShop extends Shop{

    @Override
    public void writeFullPrice() {
        BigDecimal fullPrice = addDiscount(getFullPriceWithVAT()).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(fullPrice);
    }

}
