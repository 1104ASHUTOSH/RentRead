package com.example.rentread.repository;

import com.example.rentread.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserIdAndReturnDateIsNull(Long userId);
}
