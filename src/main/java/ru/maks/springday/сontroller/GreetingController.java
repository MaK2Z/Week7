package ru.maks.springday.сontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maks.springday.repository.GreetingResponse;
import ru.maks.springday.service.GreetingService;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public GreetingResponse greet(
            @RequestParam(defaultValue = "World") String name) {
        return new GreetingResponse(greetingService.buildGreeting(name));
    }
}