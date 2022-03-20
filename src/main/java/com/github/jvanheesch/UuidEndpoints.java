package com.github.jvanheesch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/uuid")
@RestController
public class UuidEndpoints {
    @GetMapping(value = "/string")
    public String string() {
        return UUID.randomUUID().toString();
    }

    @GetMapping(value = "/uuid")
    public UUID json() {
        return UUID.randomUUID();
    }
}
