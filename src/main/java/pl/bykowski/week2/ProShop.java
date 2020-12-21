package pl.bykowski.week2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;


@Profile("Pro")
@Service
public class ProShop extends PlusShop{

    @Value("${discount}")
    private Integer discount;

    @Override
    public void writeFullPrice() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);

        System.out.println(nf.format(addDiscount(getFullPriceWithVAT())));
    }

    private Double addDiscount(Double price) {
        return price * (1 - (double) discount/100);
    }

}
