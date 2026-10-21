package mx.edu.utez.proyecto4D.controller.partida.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto4D.model.partida.EstadoPartida;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTiroDTO {
    private Long id;
    private Long partidaId;
    private int valorDado1;
    private int valorDado2;
    private int suma;
    private boolean esGanador;
    private EstadoPartida estadoPartida;
    private double saldoJugador;
}
