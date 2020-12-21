package pl.bykowski.week2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;


@Profile("Plus")
@Service
public class PlusShop extends Shop{

    @Value("${VAT}")
    protected Integer VAT;

    @Override
    public void writeFullPrice() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);

        System.out.println(nf.format(getFullPriceWithVAT()));
    }

    protected Double getFullPriceWithVAT() {
        return addVAT(getFullPrice());
    }

    protected Double addVAT(Integer price) {
        return ((double) VAT / 100 + 1) * price;
    }

}
