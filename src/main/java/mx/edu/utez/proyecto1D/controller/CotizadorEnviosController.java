package mx.edu.utez.proyecto1D.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.dto.EnviosRequestDTO;
import mx.edu.utez.proyecto1D.controller.dto.EnviosResponseDTO;
import mx.edu.utez.proyecto1D.service.EnviosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envios")
public class CotizadorEnviosController {

    private final EnviosService enviosService;

    public CotizadorEnviosController(EnviosService enviosService) {
        this.enviosService = enviosService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<EnviosResponseDTO> cotizar(@RequestBody @Valid EnviosRequestDTO payload) {
        return ResponseEntity.ok(enviosService.calcularCotizacion(payload));
    }
}