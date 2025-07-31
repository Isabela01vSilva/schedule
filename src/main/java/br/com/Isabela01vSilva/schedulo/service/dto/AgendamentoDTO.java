package br.com.Isabela01vSilva.schedulo.service.dto;

import java.time.LocalDateTime;

public record AgendamentoDTO(Long id,
                             String descricao,
                             LocalDateTime dataHora) {
}
