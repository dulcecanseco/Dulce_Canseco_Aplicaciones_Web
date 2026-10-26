package mx.edu.utez.proyecto1D.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestUpdateUserDTO;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers(){
        return ResponseEntity.status(200)
                .body(usuarioService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid RequestCreateUserDTO payload){
        return ResponseEntity.status(201).body(
                usuarioService.createUser(payload)
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Usuario> activarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.cambiarEstado(id, true));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.cambiarEstado(id, false));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody RequestUpdateUserDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, dto));
    }
}
