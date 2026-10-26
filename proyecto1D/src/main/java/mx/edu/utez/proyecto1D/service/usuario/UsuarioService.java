package mx.edu.utez.proyecto1D.service.usuario;

import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestUpdateUserDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.model.usuario.Roles;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1D.repository.usuarios.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    // servicios
    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario createUser(RequestCreateUserDTO payload){
        //validaciones

        if(usuarioRepository.findByUsername(payload.getUsername()).isPresent() ){
            throw new CustomBadRequestException("El username ya esta en uso");
        }
        //registro
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(payload.getNombres());
        nuevaPersona.setPrimerApellido(payload.getPrimerApellido());
        nuevaPersona.setSegundoApellido(payload.getSegundoApellido());
        nuevaPersona.setCorreo(payload.getCorreo());
        nuevaPersona.setCurp(payload.getCurp());
        nuevaPersona.setFechaNacimiento(payload.getFechaNacimenito());

        nuevaPersona = personaRepository.save(nuevaPersona);

        //Crear la instancia de usuario que vamos a guardar
        //a partir de los datos del dto
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(payload.getUsername());
        nuevoUsuario.setPassword(payload.getPassword());
        nuevoUsuario.setDescripcion(payload.getDescripcion());
        nuevoUsuario.setEnable(true);
        nuevoUsuario.setRol(
                Roles.valueOf(payload.getRol())
        );
        nuevoUsuario.setPersona(nuevaPersona);

        return usuarioRepository.save(nuevoUsuario);
    }


    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("El usuario con ID " + id + " no existe"));
    }

    @Transactional
    public Usuario cambiarEstado(Long id, boolean estado) {
        Usuario usuario = obtenerPorId(id);
        usuario.setEnable(estado);
        return usuarioRepository.save(usuario);
    }


    @Transactional
    public Usuario actualizarUsuario(Long id, RequestUpdateUserDTO dto) {
        Usuario usuarioExistente = obtenerPorId(id);

     Optional<Usuario> usuarioConMismoUsername = usuarioRepository.findByUsername(dto.getUsername());
        if (usuarioConMismoUsername.isPresent() && usuarioConMismoUsername.get().getId() != id) {
            throw new CustomBadRequestException("El nombre de usuario '" + dto.getUsername() + "' ya está ocupado");
        }


        usuarioExistente.setUsername(dto.getUsername());
        usuarioExistente.setDescripcion(dto.getDescripcion());

       return usuarioRepository.save(usuarioExistente);
    }
}
