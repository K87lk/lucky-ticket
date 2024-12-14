package org.example.streams;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Genre {

        ACTION("Action"),
        ADVENTURE("Adventure"),
        ANIMATION("Animation"),
        ART("Art"),
        BIOGRAPHY("Biography"),
        COMICS("Comics"),
        CRIME("Crime"),
        DETECTIVE("Detective"),
        FANTASY("Fantasy"),
        HORROR("Horror"),
        ROMANCE("Romance"),
        THRILLER("Thriller"),
        DRAMA("Drama"),
        MYSTERY("Mystery"),
        FICTION("Fiction"),
        POETRY("Poetry"),
        COMEDY("Comedy"),
        TRAGEDY("Tragedy"),
        CLASSIC("Classic"),
        FABLE("Fable"),
        LEGEND("Legend"),
        MYTH("Myth"),
        PARABLE("Parable"),
        SATIRE("Satire"),
        TALE("Tale");

    private final String name;

}
