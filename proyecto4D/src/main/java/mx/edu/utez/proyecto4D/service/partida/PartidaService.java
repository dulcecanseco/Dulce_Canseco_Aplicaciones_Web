package mx.edu.utez.proyecto4D.service.partida;

import mx.edu.utez.proyecto4D.controller.partida.dto.RequestIniciarPartidaDTO;
import mx.edu.utez.proyecto4D.controller.partida.dto.ResponsePartidaDTO;
import mx.edu.utez.proyecto4D.controller.partida.dto.ResponseTiroDTO;
import mx.edu.utez.proyecto4D.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto4D.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.proyecto4D.model.jugador.Jugador;
import mx.edu.utez.proyecto4D.model.partida.EstadoPartida;
import mx.edu.utez.proyecto4D.model.partida.Partida;
import mx.edu.utez.proyecto4D.model.tiro.Tiro;
import mx.edu.utez.proyecto4D.repository.jugador.JugadorRepository;
import mx.edu.utez.proyecto4D.repository.partida.PartidaRepository;
import mx.edu.utez.proyecto4D.repository.tiro.TiroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;
    private final TiroRepository tiroRepository;
    private final Random random = new Random();

    public PartidaService(PartidaRepository partidaRepository, JugadorRepository jugadorRepository, TiroRepository tiroRepository) {
        this.partidaRepository = partidaRepository;
        this.jugadorRepository = jugadorRepository;
        this.tiroRepository = tiroRepository;
    }

    @Transactional
    public ResponsePartidaDTO iniciarPartida(RequestIniciarPartidaDTO payload) {
        Jugador jugador = jugadorRepository.findById(payload.getJugadorId())
                .orElseThrow(() -> new CustomNotFoundException("El jugador no existe"));

        if (!jugador.isActivo()) {
            throw new CustomBadRequestException("El jugador no esta activo");
        }
        if (jugador.getSaldo() < payload.getApuesta()) {
            throw new CustomBadRequestException("Saldo insuficiente");
        }

        jugador.setSaldo(jugador.getSaldo() - payload.getApuesta());
        jugadorRepository.save(jugador);

        Partida partida = new Partida();
        partida.setFecha(LocalDateTime.now());
        partida.setEstado(EstadoPartida.EN_JUEGO);
        partida.setJugador(jugador);
        partida.setApuesta(payload.getApuesta());

        return toDTO(partidaRepository.save(partida));
    }

    @Transactional
    public ResponseTiroDTO realizarTiro(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new CustomNotFoundException("La partida no existe"));

        if (partida.getEstado() != EstadoPartida.EN_JUEGO) {
            throw new CustomBadRequestException("La partida no esta en juego");
        }

        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int suma = dado1 + dado2;
        boolean gana = suma == 7 || suma == 11;
        boolean pierde = suma == 2 || suma == 3 || suma == 12;

        Jugador jugador = partida.getJugador();

        if (gana) {
            partida.setEstado(EstadoPartida.FINALIZADA);
            jugador.setSaldo(jugador.getSaldo() + partida.getApuesta() * 2);
            jugadorRepository.save(jugador);
            partidaRepository.save(partida);
        } else if (pierde) {
            partida.setEstado(EstadoPartida.FINALIZADA);
            partidaRepository.save(partida);
        }

        Tiro tiro = new Tiro();
        tiro.setPartida(partida);
        tiro.setValorDado1(dado1);
        tiro.setValorDado2(dado2);
        tiro.setSuma(suma);
        tiro.setEsGanador(gana);
        tiro = tiroRepository.save(tiro);

        return new ResponseTiroDTO(
                tiro.getId(),
                partida.getId(),
                dado1,
                dado2,
                suma,
                gana,
                partida.getEstado(),
                jugador.getSaldo()
        );
    }

    @Transactional
    public ResponsePartidaDTO finalizarPartidaManual(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new CustomNotFoundException("La partida no existe"));

        if (partida.getEstado() == EstadoPartida.FINALIZADA) {
            throw new CustomBadRequestException("La partida ya esta finalizada");
        }

        partida.setEstado(EstadoPartida.FINALIZADA);
        return toDTO(partidaRepository.save(partida));
    }

    public List<ResponsePartidaDTO> obtenerHistorialPartida(Long jugadorId) {
        if (!jugadorRepository.existsById(jugadorId)) {
            throw new CustomNotFoundException("El jugador no existe");
        }

        return partidaRepository.findByJugadorId(jugadorId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private ResponsePartidaDTO toDTO(Partida partida) {
        return new ResponsePartidaDTO(
                partida.getId(),
                partida.getFecha(),
                partida.getEstado(),
                partida.getJugador().getId(),
                partida.getApuesta()
        );
    }
}
