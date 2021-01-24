package pl.bykowski.week2.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.bykowski.week2.entity.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
public abstract class Shop {

    @Value("${VAT}")
    protected Integer VAT;

    @Value("${discount}")
    private Integer discount;

    protected final List<Product> products = new LinkedList<>();
    protected final Random random = new Random();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        prepareSampleProducts();
        writeFullPrice();
    }

    protected void prepareSampleProducts() {
        Product product1 = new Product("Product1", generateRandomPrice());
        Product product2 = new Product("Product2", generateRandomPrice());
        Product product3 = new Product("Product3", generateRandomPrice());
        Product product4 = new Product("Product4", generateRandomPrice());
        Product product5 = new Product("Product5", generateRandomPrice());

        addProducts(product1, product2, product3, product4, product5);
    }

    protected BigDecimal generateRandomPrice() {
        return BigDecimal.valueOf(random.nextInt(251) + 50);
    }

    protected void addProducts(Product... product) {
        Arrays
                .stream(product)
                .filter(this::isValidated)
                .forEach(products::add);
    }

    private boolean isValidated(Product product) {
        if(product.isValidated()) return true;
        else log.warn("Product is not correct!");

        return false;
    }

    protected abstract void writeFullPrice();

    protected BigDecimal getFullPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected BigDecimal getFullPriceWithVAT() {
        return addVAT(getFullPrice());
    }

    protected BigDecimal addVAT(BigDecimal price) {
        BigDecimal vat = BigDecimal.valueOf(((double) VAT / 100) + 1);
        return price.multiply(vat);
    }

    protected BigDecimal addDiscount(BigDecimal price) {
        BigDecimal discount = BigDecimal.valueOf(1 -  ((double) this.discount/100));
        return price.multiply(discount);
    }


}



