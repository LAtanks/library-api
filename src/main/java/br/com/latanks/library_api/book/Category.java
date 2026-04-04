package br.com.latanks.library_api.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    
    FICTION("Fiction"),
    NON_FICTION("Non-Fiction"),
    SCIENCE_FICTION("Science Fiction"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    BIOGRAPHY("Biography"),
    HISTORY("History"),
    CHILDREN("Children"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense"),
    Horror("Horror"),
    YOUNG_ADULT("Young Adult"),
    BIOGRAFY("Biografy"),
    ACADEMIC("Academic"),
    BUSINESS("Business"),
    COMICS("Comics"),
    MANGA("Manga");

    private final String displayName;
}
