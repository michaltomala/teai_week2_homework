package pl.bykowski.week2.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Profile("Plus")
@Service
public class PlusShop extends Shop{

    @Override
    public void writeFullPrice() {
        BigDecimal fullPrice = getFullPriceWithVAT().setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(fullPrice);
    }

}
