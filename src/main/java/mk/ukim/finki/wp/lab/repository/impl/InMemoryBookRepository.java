package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository {

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(text.toLowerCase()))
                .filter(book -> book.getAverageRating() >= rating)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public Book save(Book book) {
        // If book with this ID exists, update it
        Optional<Book> existingBook = findById(book.getId());
        if (existingBook.isPresent()) {
            DataHolder.books.remove(existingBook.get());
        }
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }
}