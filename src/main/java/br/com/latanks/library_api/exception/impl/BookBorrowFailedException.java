package br.com.latanks.library_api.exception.impl;

public class BookBorrowFailedException extends RuntimeException{
    public BookBorrowFailedException(String message) {
        super(message);
    }
}
