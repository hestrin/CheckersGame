package com.mainApp.services.abcConjecture;

import com.model.abc.AbcVerificationCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbcConjectureService {
    List<AbcVerificationCase> getVerifiedAbcTripletsLowerThan(int limit);
}
