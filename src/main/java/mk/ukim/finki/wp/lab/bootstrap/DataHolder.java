package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize authors
        authors = new ArrayList<>();
        Author author1 = new Author("Harper", "Lee", "USA", "American novelist widely known for To Kill a Mockingbird");
        Author author2 = new Author("George", "Orwell", "UK", "English novelist, essayist, journalist and critic");
        Author author3 = new Author("J.R.R.", "Tolkien", "UK", "English writer, poet, philologist, and academic");
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        // Initialize books with authors
        books = new ArrayList<>();
        books.add(new Book("To Kill a Mockingbird", "Fiction", 4.8, author1));
        books.add(new Book("1984", "Dystopian", 4.7, author2));
        books.add(new Book("The Great Gatsby", "Classic", 4.4, author1));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9, author3));
        books.add(new Book("The Catcher in the Rye", "Fiction", 4.2, author2));
        books.add(new Book("Pride and Prejudice", "Romance", 4.6, author1));
        books.add(new Book("The Hobbit", "Fantasy", 4.7, author3));
        books.add(new Book("Brave New World", "Dystopian", 4.3, author2));
        books.add(new Book("The Lord of the Rings", "Fantasy", 4.9, author3));
        books.add(new Book("Animal Farm", "Satire", 4.5, author2));

        reservations = new ArrayList<>();
    }
}