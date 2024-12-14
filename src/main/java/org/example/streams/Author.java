package org.example.streams;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
   private String name;
   private List<Book> books;
   private int age;
   private Country country;
   private int awards;
   private int activeYears;

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("Author{");
      sb.append("name='").append(name).append('\'');
      sb.append(", books=").append(books.stream().map(Book::getTitle).toList());
      sb.append(", age=").append(age);
      sb.append(", country=").append(country);
      sb.append(", awards=").append(awards);
      sb.append(", activeYears=").append(activeYears);
      sb.append('}');
      return sb.toString();
   }
}
