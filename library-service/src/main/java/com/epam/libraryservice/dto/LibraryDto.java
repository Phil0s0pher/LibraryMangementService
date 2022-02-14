package com.epam.libraryservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {
    private int libraryId;
    private String userName;
    private int bookId;
}
