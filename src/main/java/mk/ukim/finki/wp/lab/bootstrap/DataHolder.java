package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.Cover;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        Author author4 = new Author("F. Scott", "Fitzgerald", "USA", "American novelist and short story writer");
        Author author5 = new Author("J.K.", "Rowling", "UK", "British author, best known for Harry Potter series");

        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);
        author3 = authorRepository.save(author3);
        author4 = authorRepository.save(author4);
        author5 = authorRepository.save(author5);

        // Initialize books with multiple authors and save to database
        bookRepository.save(new Book("To Kill a Mockingbird", "Fiction", 4.8, Cover.HARD, LocalDate.of(1960, 7, 11), Arrays.asList(author1)));
        bookRepository.save(new Book("1984", "Dystopian", 4.7, Cover.SOFT, LocalDate.of(1949, 6, 8), Arrays.asList(author2)));
        bookRepository.save(new Book("The Great Gatsby", "Classic", 4.4, Cover.HARD, LocalDate.of(1925, 4, 10), Arrays.asList(author4)));
        bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9, Cover.HARD, LocalDate.of(1997, 6, 26), Arrays.asList(author5)));
        bookRepository.save(new Book("The Catcher in the Rye", "Fiction", 4.2, Cover.SOFT, LocalDate.of(1951, 7, 16), Arrays.asList(author1, author2)));
        bookRepository.save(new Book("Pride and Prejudice", "Romance", 4.6, Cover.HARD, LocalDate.of(1813, 1, 28), Arrays.asList(author1)));
        bookRepository.save(new Book("The Hobbit", "Fantasy", 4.7, Cover.SOFT, LocalDate.of(1937, 9, 21), Arrays.asList(author3)));
        bookRepository.save(new Book("Brave New World", "Dystopian", 4.3, Cover.SOFT, LocalDate.of(1932, 8, 30), Arrays.asList(author2)));
        bookRepository.save(new Book("The Lord of the Rings", "Fantasy", 4.9, Cover.HARD, LocalDate.of(1954, 7, 29), Arrays.asList(author3)));
        bookRepository.save(new Book("Animal Farm", "Satire", 4.5, Cover.SOFT, LocalDate.of(1945, 8, 17), Arrays.asList(author2)));

        reservations = new ArrayList<>();
    }
}