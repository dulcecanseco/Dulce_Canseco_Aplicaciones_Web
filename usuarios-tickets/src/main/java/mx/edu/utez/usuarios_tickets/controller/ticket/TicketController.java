package mx.edu.utez.usuarios_tickets.controller.ticket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.usuarios_tickets.controller.ticket.dto.RequestCreateTicketDTO;
import mx.edu.utez.usuarios_tickets.controller.ticket.dto.RequestUpdatePrioridadTicketDTO;
import mx.edu.utez.usuarios_tickets.model.Ticket;
import mx.edu.utez.usuarios_tickets.service.ticket.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;


    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody RequestCreateTicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setPrioridad(dto.getPrioridad());

        return new ResponseEntity<>(ticketService.crearTicket(ticket, dto.getUsuarioId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/siguiente-estado")
    public ResponseEntity<Ticket> actualizarEstadoTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(
            @PathVariable Long id,
            @Valid @RequestBody RequestUpdatePrioridadTicketDTO dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, dto.getPrioridad()));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}