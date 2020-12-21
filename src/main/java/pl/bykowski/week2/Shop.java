package pl.bykowski.week2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


@Slf4j
@Profile("Basic")
@Service
public class Shop {

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

    protected Integer generateRandomPrice() {
        return random.nextInt(251) + 50;
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

    public void writeFullPrice() {
        System.out.println(getFullPrice());
    }

    protected Integer getFullPrice() {
        return products
            .stream()
            .mapToInt(Product::getPrice)
            .sum();
    }

}
