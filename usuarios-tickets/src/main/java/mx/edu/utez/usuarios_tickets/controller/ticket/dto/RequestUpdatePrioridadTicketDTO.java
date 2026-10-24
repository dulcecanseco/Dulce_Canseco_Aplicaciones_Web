package mx.edu.utez.usuarios_tickets.controller.ticket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.usuarios_tickets.model.PrioridadTicket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdatePrioridadTicketDTO {

    @NotNull(message = "La nueva prioridad es obligatoria")
    private PrioridadTicket prioridad;
}