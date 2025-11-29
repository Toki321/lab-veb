package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<BookReservation> reservations = new ArrayList<>();

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        // Only initialize if database is empty
        if (authorRepository.count() > 0) {
            return;
        }

        // Initialize authors and save to database
        Author author1 = new Author("Harper", "Lee", "USA", "American novelist widely known for To Kill a Mockingbird");
        Author author2 = new Author("George", "Orwell", "UK", "English novelist, essayist, journalist and critic");
        Author author3 = new Author("J.R.R.", "Tolkien", "UK", "English writer, poet, philologist, and academic");

        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);
        author3 = authorRepository.save(author3);

        // Initialize books with authors and save to database
        bookRepository.save(new Book("To Kill a Mockingbird", "Fiction", 4.8, author1));
        bookRepository.save(new Book("1984", "Dystopian", 4.7, author2));
        bookRepository.save(new Book("The Great Gatsby", "Classic", 4.4, author1));
        bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9, author3));
        bookRepository.save(new Book("The Catcher in the Rye", "Fiction", 4.2, author2));
        bookRepository.save(new Book("Pride and Prejudice", "Romance", 4.6, author1));
        bookRepository.save(new Book("The Hobbit", "Fantasy", 4.7, author3));
        bookRepository.save(new Book("Brave New World", "Dystopian", 4.3, author2));
        bookRepository.save(new Book("The Lord of the Rings", "Fantasy", 4.9, author3));
        bookRepository.save(new Book("Animal Farm", "Satire", 4.5, author2));

        reservations = new ArrayList<>();
    }
}