package com.example.CalcMinCoins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @PostMapping("/calculate")
    public ResponseEntity<List<Float>> calculateMinimumCoins(@RequestBody CoinRequest coinRequest) {
        float amount = coinRequest.getAmount();
        List<Float> denominations = coinRequest.getDenominations();

        // Validating input amount
        if (amount < 0 || amount > 10000.00f) {
            return ResponseEntity.badRequest().body(null);
        }

        // Validating and sort denominations
        List<Float> availableDenominations = Arrays.asList(1000f, 100f, 50f, 10f, 5f, 2f, 1f, 0.5f, 0.2f, 0.1f, 0.05f, 0.01f);
        for (Float denomination : denominations) {
            if (!availableDenominations.contains(denomination)) {
                return ResponseEntity.badRequest().body(null);
            }
        }
        Collections.sort(denominations, Collections.reverseOrder());

        // Calculate minimum coins
        List<Float> usedCoins = coinService.getMinimumCoins(amount, denominations);
        return ResponseEntity.ok().body(usedCoins);
    }
    
    @GetMapping("/test")
    public String test() {
        return "MinCoins API is working!";
    }
}
