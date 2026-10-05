package mx.edu.utez.proyecto1D.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.dto.RentaRequestDTO;
import mx.edu.utez.proyecto1D.controller.dto.RentaResponseDTO;
import mx.edu.utez.proyecto1D.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/renta")
public class CotizadorRentaController {

    private final RentaService rentaService;

    public CotizadorRentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<RentaResponseDTO> cotizar(@RequestBody @Valid RentaRequestDTO payload) {
        return ResponseEntity.ok(rentaService.calcularRenta(payload));
    }
}