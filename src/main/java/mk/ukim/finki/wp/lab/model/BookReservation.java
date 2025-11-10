package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
public class BookReservation {


    public BookReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies) {
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
    }

    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;
}