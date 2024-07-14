package com.example.CalcMinCoins;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CoinService {

    public List<Float> getMinimumCoins(float amount, List<Float> coinDenominations) {
        List<Float> usedCoins = new ArrayList<>();
        float remainingAmount = amount;

        for (Float coin : coinDenominations) {
            if (remainingAmount <= 0) break; // Exit loop if amount is fully covered
            if (coin <= remainingAmount) {
                int count = (int) (remainingAmount / coin);
                for (int i = 0; i < count; i++) {
                    usedCoins.add(coin);
                }
                remainingAmount %= coin; // Update remaining amount
                remainingAmount = Math.round(remainingAmount * 100) / 100f; // Handle floating point precision
            }
        }

        // Add smaller denominations if there's any remaining amount due to precision issues
        if (remainingAmount > 0.001) {
            for (Float coin : coinDenominations) {
                while (coin <= remainingAmount && remainingAmount > 0.001) {
                    usedCoins.add(coin);
                    remainingAmount -= coin;
                    remainingAmount = Math.round(remainingAmount * 100) / 100f; // Handle floating point precision
                }
            }
        }

        return usedCoins;
    }
}
