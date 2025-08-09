package br.com.Isabela01vSilva.schedulo.model.appointment;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Table(name = "agendamento")
@Entity
public class Appointment {
    public Appointment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_agendamento")
    private Long id;

    @Column(name = "data_execucao")
    private LocalDate executionDate;

    @Column(columnDefinition = "jsonb")
    @Type(JsonType.class)
    private JsonNode payload;

    @Column(name = "nome_aplicacao")
    private String appName;


    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String apiName) {
        this.appName = apiName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
