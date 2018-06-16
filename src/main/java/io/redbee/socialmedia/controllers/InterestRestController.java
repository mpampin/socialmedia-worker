package io.redbee.socialmedia.controllers;

import io.redbee.socialmedia.services.impl.InterestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interests")
public class InterestRestController {

    private InterestServiceImpl interestService;

    @Autowired
    public InterestRestController(InterestServiceImpl interestService) {
        this.interestService = interestService;
    }

    @RequestMapping("/")
    public String getInterests() {
        return "";
    }

}
