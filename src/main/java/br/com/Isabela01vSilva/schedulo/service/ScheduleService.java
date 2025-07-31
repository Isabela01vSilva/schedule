package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.service.dto.AgendamentoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleService {

    public AgendamentoDTO buscarPorId(Long id) {
        // Simula retorno de dados (substitua com chamada ao repositório, se for o caso)
        return new AgendamentoDTO(id, "Consulta de rotina", LocalDateTime.now().plusDays(2));
    }
}
