package Senet.Ramboot.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomService {
    
    public int getRandomInt(int min, int max) {
        return (int) (ThreadLocalRandom.current().nextDouble() * (max - min + 1) + min);
    }

    public double getRandomDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }



}
