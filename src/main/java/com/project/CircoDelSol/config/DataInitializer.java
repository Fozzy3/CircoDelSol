package com.project.CircoDelSol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.CircoDelSol.entity.Award;
import com.project.CircoDelSol.reposity.AwardRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AwardRepository awardRepository;

    @Override
    public void run(String... args) throws Exception {
        if (awardRepository.count() == 0) {
            awardRepository.save(new Award((long) 1, "Mayor", 500));
            awardRepository.save(new Award((long) 2, "Menor", 10000));
        }
    }
}
