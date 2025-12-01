package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private double averageRating;

    @Enumerated(EnumType.STRING)
    private Cover cover;

    private LocalDate publicationDate;

    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book(String title, String genre, double averageRating, Cover cover, LocalDate publicationDate) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.cover = cover;
        this.publicationDate = publicationDate;
    }

    public Book(String title, String genre, double averageRating, Cover cover, LocalDate publicationDate, List<Author> authors) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.cover = cover;
        this.publicationDate = publicationDate;
        this.authors = authors;
    }
}