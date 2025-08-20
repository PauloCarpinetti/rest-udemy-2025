package com.github.paulocarpinetti.rest_udemy_2025.exceptions.handler;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
