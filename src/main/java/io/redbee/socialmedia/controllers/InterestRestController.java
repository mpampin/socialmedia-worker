package io.redbee.socialmedia.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interests")
public class InterestRestController {

    @RequestMapping("/")
    public String getInterests() {
        return "hola mundo";
    }

}
