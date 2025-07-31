package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.service.ScheduleService;
import br.com.Isabela01vSilva.schedulo.service.dto.AgendamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamentos")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> buscarAgendamento(@PathVariable Long id) {
        AgendamentoDTO dto = scheduleService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }
}
