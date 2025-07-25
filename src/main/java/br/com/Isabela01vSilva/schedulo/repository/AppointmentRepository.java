package br.com.Isabela01vSilva.schedulo.repository;

import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByExecutionDate(LocalDate now);
}
