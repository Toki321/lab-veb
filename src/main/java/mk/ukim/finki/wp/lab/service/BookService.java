package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Cover;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    List<Book> findBooksByAuthorId(Long authorId);
    Optional<Book> findById(Long id);
    Book save(String title, String genre, Double averageRating, Cover cover, LocalDate publicationDate, List<Long> authorIds);
    Book update(Long id, String title, String genre, Double averageRating, Cover cover, LocalDate publicationDate, List<Long> authorIds);
    void deleteById(Long id);
}