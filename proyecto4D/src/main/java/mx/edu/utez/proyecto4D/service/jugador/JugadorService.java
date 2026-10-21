package mx.edu.utez.proyecto4D.service.jugador;

import mx.edu.utez.proyecto4D.controller.jugador.dto.RequestCreateJugadorDTO;
import mx.edu.utez.proyecto4D.controller.jugador.dto.RequestRecargaDTO;
import mx.edu.utez.proyecto4D.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.proyecto4D.model.jugador.Jugador;
import mx.edu.utez.proyecto4D.repository.jugador.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {
    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    public Jugador createJugador(RequestCreateJugadorDTO payload) {
        Jugador jugador = new Jugador();
        jugador.setNombre(payload.getNombre());
        jugador.setSaldo(payload.getSaldo());
        jugador.setActivo(true);
        return jugadorRepository.save(jugador);
    }

    public Jugador recargarSaldo(Long jugadorId, RequestRecargaDTO payload) {
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new CustomNotFoundException("El jugador no existe"));

        jugador.setSaldo(jugador.getSaldo() + payload.getMonto());
        return jugadorRepository.save(jugador);
    }
}
