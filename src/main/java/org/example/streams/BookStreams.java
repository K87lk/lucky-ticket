package org.example.streams;

import net.datafaker.Faker;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookStreams {
    public static void main(String[] args) {
        var books = createBooks(30, 10);

        var booksAfterACertainYear = books.stream()
                .filter(book -> book.getYear() > 2000)
                .toList();
//        System.out.println(booksAfterACertainYear);

        var booksByGenre = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
//        System.out.println(booksByGenre);

        var bookMap = IntStream.range(0, books.size())
                .boxed()
                .collect(Collectors.toMap(i -> i + 1, books::get));
//        System.out.println(bookMap);

        var books2 = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
//        System.out.println(books2);

        var books3 = books.stream()
                .collect(Collectors.toMap(
                        Book::getGenre,
                        Function.identity(),
                        (existing, replacement) -> existing));
//        System.out.println(books3);

        var mostExpensiveBook = books.stream()
                .max(Comparator.comparing(Book::getPrice))
                .orElse(null);

//        System.out.println(mostExpensiveBook);

        var authorsFromSpecificCounty = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .distinct()
                .collect(
                        Collectors.groupingBy(
                                author -> author.getCountry().getName(),
                                Collectors.mapping(Author::getName, Collectors.toList())
                        )
                );
//        System.out.println(authorsFromSpecificCounty);

        var authorsByCountrySorted = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .distinct()
                .collect(Collectors.groupingBy(
                                author -> author.getCountry().getName(),
                                Collectors.mapping(Author::getName,
                                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                                                    list.sort(Comparator.naturalOrder());
                                                    return list;
                                                }
                                        )
                                )
                        )
                );
//        System.out.println(authorsByCountrySorted);

        var sortedAuthorsByCountry = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .distinct()
                .collect(Collectors.groupingBy(author -> author.getCountry().getName(),
                        Collectors.mapping(Author::getName, Collectors.toList())))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println(sortedAuthorsByCountry);

    }

    public static List<Book> createBooks(int numBooks, int numAuthors) {
        Faker faker = new Faker();
        Random random = new Random();
        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<>();


        for (int i = 0; i < numAuthors; i++) {
            Author author = new Author();
            author.setName(faker.name().fullName());
            author.setAge(random.nextInt(90) + 18);
            author.setAwards(random.nextInt(20));
            author.setCountry(Country.values()[random.nextInt(Country.values().length)]);
            author.setBooks(new ArrayList<>());

            authors.add(author);
        }

        for (int i = 0; i < numBooks; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setYear(random.nextInt(320) + 1700);
            book.setIsbn(faker.code().isbn10());
            book.setGenre(Genre.values()[random.nextInt(Genre.values().length)]);
            book.setPrice(BigDecimal.valueOf(random.nextDouble() * 100));

            int numAuthorsForBook = random.nextInt(Math.min(authors.size(), 3)) + 1;
            List<Author> authorsForBook = new ArrayList<>();
            for (int j = 0; j < numAuthorsForBook; j++) {
                Author randomAuthor = authors.get(random.nextInt(authors.size()));
                if (!authorsForBook.contains(randomAuthor)) {
                    authorsForBook.add(randomAuthor);
                    randomAuthor.getBooks().add(book);
                }
            }
            book.setAuthors(authorsForBook);
            books.add(book);
        }
        return books;
    }
}
