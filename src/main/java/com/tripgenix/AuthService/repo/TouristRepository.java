package com.tripgenix.AuthService.repo;

import com.tripgenix.AuthService.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Integer> {

}
