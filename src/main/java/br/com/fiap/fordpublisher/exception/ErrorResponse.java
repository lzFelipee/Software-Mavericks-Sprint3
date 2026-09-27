package br.com.fiap.fordpublisher.exception;

public record ErrorResponse(
        int status,
        String message
) {
}