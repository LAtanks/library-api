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
    CHILDREN("Children");

    private final String displayName;
}
