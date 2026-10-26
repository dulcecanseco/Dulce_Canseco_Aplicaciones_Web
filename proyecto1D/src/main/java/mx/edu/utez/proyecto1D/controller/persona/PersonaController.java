package mx.edu.utez.proyecto1D.controller.persona;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.service.persona.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(
            @PathVariable Long id,
            @Valid @RequestBody RequestUpdatePersonaDTO dto) {
        return ResponseEntity.ok(personaService.actualizarPersona(id, dto));
    }
}