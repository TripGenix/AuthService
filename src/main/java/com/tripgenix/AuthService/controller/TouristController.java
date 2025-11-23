package com.tripgenix.AuthService.controller;

import com.tripgenix.AuthService.dto.TouristDto;
import com.tripgenix.AuthService.dto.TouristResponseDto;
import com.tripgenix.AuthService.services.TouristService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/tourists")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @PostMapping("/register")
    public ResponseEntity<TouristResponseDto> register(@RequestBody TouristDto dto){
        TouristResponseDto response = touristService.saveTourist(dto);
        return ResponseEntity.ok(response);
    }
}
