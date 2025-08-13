package br.com.Isabela01vSilva.schedulo.model.appointment;

public enum Status {
    AGENDADO,  // agendamento criado, ainda não processado
    PRONTO,    // agendamento processado e pronto
    CANCELADO, // caso queira permitir cancelamento
    CONCLUIDO  // quando o agendamento já foi executado
}
