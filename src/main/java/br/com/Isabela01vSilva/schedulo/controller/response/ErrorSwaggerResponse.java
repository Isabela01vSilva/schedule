package br.com.Isabela01vSilva.schedulo.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta de erro padrão")
public record ErrorSwaggerResponse(
        @Schema(description = "Código do status HTTP", example = "404")
        int status,

        @Schema(description = "Mensagem descritiva do erro", example = "Agendamento não encontrado")
        String message,

        @Schema(description = "Data e hora do erro", example = "2025-08-11T18:30:04.558Z")
        LocalDateTime timestamp
) {
}
