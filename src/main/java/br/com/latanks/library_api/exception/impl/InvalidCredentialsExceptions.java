package br.com.latanks.library_api.exception.impl;

public class InvalidCredentialsExceptions extends RuntimeException {
    public InvalidCredentialsExceptions(String message) {
        super(message);
    }
}