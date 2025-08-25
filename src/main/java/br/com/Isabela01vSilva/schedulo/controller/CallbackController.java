package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.service.CallbackService;
import br.com.Isabela01vSilva.schedulo.service.dto.CallbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/appointments/callback")
public class CallbackController {

    @Autowired
    private CallbackService callbackService;

    @PutMapping
    public ResponseEntity<String> receberCallback(@RequestBody CallbackDTO callback) {
        callbackService.receberCallback(callback);
        return ResponseEntity.ok("Callback recebido");
    }
}
