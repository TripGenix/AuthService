package com.tripgenix.AuthService.services;


import com.tripgenix.AuthService.dto.TouristDto;
import com.tripgenix.AuthService.dto.TouristResponseDto;
import com.tripgenix.AuthService.model.Tourist;
import com.tripgenix.AuthService.repo.TouristRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TouristService {
    @Autowired
    private TouristRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<TouristDto> getAllUsers() {
        List<Tourist> users = userRepository.findAll();
        return modelMapper.map(users, new TypeToken<List<TouristDto>>() {}.getType());
    }

    public TouristResponseDto saveTourist(TouristDto touristDto) {
        Tourist user = modelMapper.map(touristDto, Tourist.class);

        if (touristDto.getDateOfBirth() != null && !touristDto.getDateOfBirth().isEmpty()) {
            user.setDateOfBirth(LocalDate.parse(touristDto.getDateOfBirth()));
        }
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(touristDto.getPassword()));

        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        user = userRepository.save(user);
        return modelMapper.map(user, TouristResponseDto.class);
    }



}
