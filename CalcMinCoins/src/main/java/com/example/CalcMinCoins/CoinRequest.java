package com.example.CalcMinCoins;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class CoinRequest {

    @DecimalMin(value = "0", message = "Amount must be greater than or equal to 0")
    @DecimalMax(value = "10000", message = "Amount must be less than or equal to 10000")
    private float amount;

    @NotEmpty(message = "Denominations list must not be empty")
    private List<Float> denominations;

    // Getters and setters
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public List<Float> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Float> denominations) {
        this.denominations = denominations;
    }
}
