package com.model;

public enum ConjectureVerificationStatus {
   UNVERIFIED(0),
    VERIFIED(1),
    FAILED(2),
    REDUCTIBLE(4);

    private int status;

    ConjectureVerificationStatus(int status) {
        this.status = status;
    }
}
