package mx.edu.utez.usuarios_tickets.service.usuario;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.usuarios_tickets.exception.customExceptions.ResourceNotFoundException;
import mx.edu.utez.usuarios_tickets.model.Usuario;
import mx.edu.utez.usuarios_tickets.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + id + " no existe."));
    }

    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}