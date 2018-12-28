package com.mainApp.services.abcConjecture.impl;

import com.mainApp.services.abcConjecture.AbcConjectureService;
import com.model.abc.AbcVerificationCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan
@Component
public class AbcConjectureServiceImpl implements AbcConjectureService {

    public AbcConjectureServiceImpl() {
    }

    public List<AbcVerificationCase> getVerifiedAbcTripletsLowerThan(int limit)
    {
        List<AbcVerificationCase> list = new ArrayList<AbcVerificationCase>();
        list.add(new AbcVerificationCase());
        list.add(new AbcVerificationCase());
        list.add(new AbcVerificationCase());
        return list;
    }
}
