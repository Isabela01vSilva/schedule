package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.service.CallbackService;
import br.com.Isabela01vSilva.schedulo.service.dto.CallbackDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/appointments/callback")
public class CallbackController {

    @Autowired
    private CallbackService callbackService;

    @Operation(
            summary = "Receber callback",
            description = "Recebe o callback enviado por outra API e atualiza o status do agendamento no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Callback recebido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping
    public ResponseEntity<String> handleCallback(@RequestBody CallbackDTO callback) {
        callbackService.handleCallback(callback);
        return ResponseEntity.ok("Callback recebido");
    }
}