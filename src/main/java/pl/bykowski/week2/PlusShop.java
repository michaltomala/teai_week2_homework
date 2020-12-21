package pl.bykowski.week2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Profile("Plus")
@Service
public class PlusShop extends Shop{

    @Value("${VAT}")
    protected Integer VAT;

    @Override
    public void writeFullPrice() {
        BigDecimal fullPrice = getFullPriceWithVAT().setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(fullPrice);
    }

    protected BigDecimal getFullPriceWithVAT() {
        return addVAT(getFullPrice());
    }

    protected BigDecimal addVAT(BigDecimal price) {
        BigDecimal vat = BigDecimal.valueOf(((double) VAT / 100) + 1);
        return price.multiply(vat);
    }

}
