package com.ironbit.invex.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiErrorResponse {
    @Schema(description = "Identifier error", example = "426fd2bc-123d-48d2-a73a-2ba5c287b1ef")
    private final String guid;
    @Schema(description = "Error code", example = "105")
    private final String errorCode;
    @Schema(description = "Error message", example = "Null value")
    private final String message;
    @Schema(description = "Status code", example = "500")
    private final Integer statusCode;
    @Schema(description = "Status name", example = "INTERNAL_SERVER_ERROR")
    private final String statusName;
    @Schema(description = "Path", example = "/api/v1/empleado")
    private final String path;
    @Schema(description = "Method", example = "DELETE")
    private final String method;
    @Schema(description = "Timestamp", example = "023-10-16T21:36:49.89")
    private final LocalDateTime timestamp;
}
