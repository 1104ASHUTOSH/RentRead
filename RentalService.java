package com.example.rentread.service;

import com.example.rentread.entity.Book;
import com.example.rentread.entity.Rental;
import com.example.rentread.entity.User;
import com.example.rentread.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Rental rentBook(User user, Book book) throws Exception {
        if (rentalRepository.findByUserIdAndReturnDateIsNull(user.getId()).size() >= 2) {
            throw new Exception("Cannot rent more than 2 books at a time");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(LocalDate.now());
        rental.setDueDate(LocalDate.now().plusWeeks(2));
        rental.setReturnDate(null);

        return rentalRepository.save(rental);
    }

    public Rental returnBook(Rental rental) {
        rental.setReturnDate(LocalDate.now());
        return rentalRepository.save(rental);
    }

    public List<Rental> getActiveRentals(Long userId) {
        return rentalRepository.findByUserIdAndReturnDateIsNull(userId);
    }
}
