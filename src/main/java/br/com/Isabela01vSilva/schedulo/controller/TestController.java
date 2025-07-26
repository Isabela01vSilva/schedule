package br.com.Isabela01vSilva.schedulo.controller;


import br.com.Isabela01vSilva.schedulo.service.ExecuteAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ExecuteAppointmentService service;

    @PostMapping
    public ResponseEntity<Void> test(){
        service.executeSchedules();
        return ResponseEntity.ok().build();
    }

}
