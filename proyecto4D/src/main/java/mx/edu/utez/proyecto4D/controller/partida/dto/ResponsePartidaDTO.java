package mx.edu.utez.proyecto4D.controller.partida.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto4D.model.partida.EstadoPartida;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePartidaDTO {
    private Long id;
    private LocalDateTime fecha;
    private EstadoPartida estado;
    private Long jugadorId;
    private double apuesta;
}
