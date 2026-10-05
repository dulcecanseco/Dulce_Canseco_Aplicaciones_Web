package mx.edu.utez.proyecto1D.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.dto.HospedajeRequestDTO;
import mx.edu.utez.proyecto1D.controller.dto.HospedajeResponseDTO;
import mx.edu.utez.proyecto1D.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospedaje")
public class CotizadorHospedajeController {

    private final HospedajeService hospedajeService;

    public CotizadorHospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<HospedajeResponseDTO> cotizar(@RequestBody @Valid HospedajeRequestDTO payload) {
        return ResponseEntity.ok(hospedajeService.calcularHospedaje(payload));
    }
}