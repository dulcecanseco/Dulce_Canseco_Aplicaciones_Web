package mx.edu.utez.proyecto4D.controller.partida;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto4D.controller.partida.dto.RequestIniciarPartidaDTO;
import mx.edu.utez.proyecto4D.controller.partida.dto.ResponsePartidaDTO;
import mx.edu.utez.proyecto4D.controller.partida.dto.ResponseTiroDTO;
import mx.edu.utez.proyecto4D.service.partida.PartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/partida")
public class PartidaController {
    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<ResponsePartidaDTO> iniciarPartida(@RequestBody @Valid RequestIniciarPartidaDTO payload) {
        return ResponseEntity.status(201).body(partidaService.iniciarPartida(payload));
    }

    @PostMapping("/{id}/tiro")
    public ResponseEntity<ResponseTiroDTO> realizarTiro(@PathVariable Long id) {
        return ResponseEntity.status(201).body(partidaService.realizarTiro(id));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ResponsePartidaDTO> finalizarPartidaManual(@PathVariable Long id) {
        return ResponseEntity.status(200).body(partidaService.finalizarPartidaManual(id));
    }

    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<ResponsePartidaDTO>> obtenerHistorialPartida(@PathVariable Long jugadorId) {
        return ResponseEntity.status(200).body(partidaService.obtenerHistorialPartida(jugadorId));
    }
}
