package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthors_Id(Long authorId);
    List<Book> findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(String title, Double rating);
}