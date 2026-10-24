package mx.edu.utez.usuarios_tickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

@Enumerated(EnumType.STRING)
    private EstadoTicket estado;

@Enumerated(EnumType.STRING)
    private PrioridadTicket prioridad;

@ManyToOne(optional = false)
@JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
