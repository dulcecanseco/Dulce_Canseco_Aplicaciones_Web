package mx.edu.utez.usuarios_tickets.service.ticket;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.usuarios_tickets.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.usuarios_tickets.exception.customExceptions.ResourceNotFoundException;
import mx.edu.utez.usuarios_tickets.model.EstadoTicket;
import mx.edu.utez.usuarios_tickets.model.PrioridadTicket;
import mx.edu.utez.usuarios_tickets.model.Ticket;
import mx.edu.utez.usuarios_tickets.model.Usuario;
import mx.edu.utez.usuarios_tickets.repository.TicketRepository;
import mx.edu.utez.usuarios_tickets.service.usuario.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioService usuarioService;


    public Ticket crearTicket(Ticket ticket, Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new CustomBadRequestException("No se puede crear el ticket. El usuario está inactivo.");
        }

        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }


    public Ticket actualizarEstadoTicket(Long id) {
        Ticket ticket = buscarPorId(id);

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (ticket.getEstado() == EstadoTicket.EN_PROCESO) {
            ticket.setEstado(EstadoTicket.CERRADO);
        } else if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new CustomBadRequestException("El ticket se encuentra CERRADO y no se puede modificar.");
        }

        return ticketRepository.save(ticket);
    }


    public Ticket reasignarPrioridad(Long id, PrioridadTicket nuevaPrioridad) {
        Ticket ticket = buscarPorId(id);

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new CustomBadRequestException("No se puede reasignar la prioridad porque el ticket está CERRADO.");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }


    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        usuarioService.buscarPorId(usuarioId);
        return ticketRepository.findByUsuarioId(usuarioId);
    }

    public Ticket buscarPorId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket con ID " + id + " no existe."));
    }
}