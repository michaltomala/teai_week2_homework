package pl.bykowski.week2.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Basic")
@Service
public class BasicShop extends Shop {

    @Override
    public void writeFullPrice() {
        System.out.println(getFullPrice());
    }
}
