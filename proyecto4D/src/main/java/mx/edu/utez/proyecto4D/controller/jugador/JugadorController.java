package mx.edu.utez.proyecto4D.controller.jugador;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto4D.controller.jugador.dto.RequestCreateJugadorDTO;
import mx.edu.utez.proyecto4D.controller.jugador.dto.RequestRecargaDTO;
import mx.edu.utez.proyecto4D.model.jugador.Jugador;
import mx.edu.utez.proyecto4D.service.jugador.JugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/jugador")
public class JugadorController {
    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> getAllJugadores() {
        return ResponseEntity.status(200).body(jugadorService.getAllJugadores());
    }

    @PostMapping
    public ResponseEntity<Jugador> createJugador(@RequestBody @Valid RequestCreateJugadorDTO payload) {
        return ResponseEntity.status(201).body(jugadorService.createJugador(payload));
    }

    @PutMapping("/{id}/recargar")
    public ResponseEntity<Jugador> recargarSaldo(@PathVariable Long id, @RequestBody @Valid RequestRecargaDTO payload) {
        return ResponseEntity.status(200).body(jugadorService.recargarSaldo(id, payload));
    }
}
