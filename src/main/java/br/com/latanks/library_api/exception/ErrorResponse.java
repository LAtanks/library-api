package br.com.latanks.library_api.exception;

import java.util.List;

public record ErrorResponse(int status, String message, List<String> errors) {
}
