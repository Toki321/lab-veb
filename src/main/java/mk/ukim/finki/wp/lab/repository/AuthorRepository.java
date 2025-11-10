package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    public List<Author> findAll() {
        return DataHolder.authors;
    }

    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }
}
