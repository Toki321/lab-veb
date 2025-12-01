package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Cover;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if (text == null || text.isEmpty()) {
            return bookRepository.findAll();
        }
        if (rating == null) {
            rating = 0.0;
        }
        return bookRepository.findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(text, rating);
    }

    @Override
    public List<Book> findBooksByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthors_Id(authorId);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Cover cover, LocalDate publicationDate, List<Long> authorIds) {
        List<Author> authors = authorRepository.findAllById(authorIds);
        if (authors.isEmpty()) {
            throw new RuntimeException("No authors found");
        }
        Book book = new Book(title, genre, averageRating, cover, publicationDate, authors);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String genre, Double averageRating, Cover cover, LocalDate publicationDate, List<Long> authorIds) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        List<Author> authors = authorRepository.findAllById(authorIds);
        if (authors.isEmpty()) {
            throw new RuntimeException("No authors found");
        }

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setCover(cover);
        book.setPublicationDate(publicationDate);
        book.setAuthors(authors);

        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}