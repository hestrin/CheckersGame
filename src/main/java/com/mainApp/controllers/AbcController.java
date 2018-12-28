package com.mainApp.controllers;

import com.mainApp.services.abcConjecture.impl.AbcConjectureServiceImpl;
import com.model.abc.AbcVerificationCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AbcController {

    @Autowired
    AbcConjectureServiceImpl abcConjectureService;

    @RequestMapping(value = "/abc",
            method=RequestMethod.GET,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AbcVerificationCase> welcome() {
        return abcConjectureService.getVerifiedAbcTripletsLowerThan(10);
    }

    public AbcConjectureServiceImpl getAbcConjectureService() {
        return abcConjectureService;
    }

    @Autowired
    public void setAbcConjectureService(AbcConjectureServiceImpl abcConjectureService) {
        this.abcConjectureService = abcConjectureService;
    }
}
