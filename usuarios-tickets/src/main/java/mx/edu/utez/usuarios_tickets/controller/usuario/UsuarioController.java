package mx.edu.utez.usuarios_tickets.controller.usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.usuarios_tickets.controller.usuario.dto.RequestCreateUsuarioDTO;
import mx.edu.utez.usuarios_tickets.model.Usuario;
import mx.edu.utez.usuarios_tickets.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody RequestCreateUsuarioDTO dto) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setActivo(true);

        return new ResponseEntity<>(usuarioService.guardarUsuario(nuevoUsuario), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.desactivarUsuario(id));
    }
}