package com.example.block6pathvariableheaders;


import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/greeting")
    public Greeting postgreeting(@RequestBody Greeting greeting) {
        return new Greeting (counter.incrementAndGet(), String.format(template, greeting.content()));
    }

    @GetMapping({"/greeting/{id}","/greeting"})
    public String getgreeting(@PathVariable(value="id") Optional<String> valor) {
        return valor.isPresent()? "Tenemos valor: " + valor.get() : "No tenemos valor";
    }

    @PutMapping("/greeting3")
    public Map putgreeting(@RequestParam(value = "var1") String var1,
                           @RequestParam(value = "var2") String var2) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(var1, var2);
        return resultMap;

    }




}


