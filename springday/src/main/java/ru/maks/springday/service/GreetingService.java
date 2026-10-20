package ru.maks.springday.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String buildGreeting (String name){
        return "Hello, " + name + "!";
    }

}
