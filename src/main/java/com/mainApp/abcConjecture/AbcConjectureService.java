package com.mainApp.abcConjecture;

import com.model.AbcVerificationCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbcConjectureService {
    List<AbcVerificationCase> getVerifiedAbcTripletsLowerThan(int limit);
}
