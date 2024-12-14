package org.example.streams;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private List<Author> authors;
    private int year;
    private Genre genre;
    private BigDecimal price;
    private String isbn;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("title='").append(title).append('\'');
        sb.append(", authors=").append(authors.stream().map(Author::getName).toList());
        sb.append(", year=").append(year);
        sb.append(", genre=").append(genre);
        sb.append(", price=").append(price);
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
