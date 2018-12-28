package com.model.abc;

public class AbcVerificationCase {
    private Integer a;
    private Integer b;
    private Integer c;
    private ConjectureVerificationStatus status = ConjectureVerificationStatus.UNVERIFIED;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public ConjectureVerificationStatus getStatus() {
        return status;
    }

    public void setStatus(ConjectureVerificationStatus status) {
        this.status = status;
    }
}
