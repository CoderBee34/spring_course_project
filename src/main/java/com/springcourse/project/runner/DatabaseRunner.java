package com.springcourse.project.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("runner is working");
    }
}
