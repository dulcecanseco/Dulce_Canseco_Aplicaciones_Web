package mx.edu.utez.proyecto1D.service.persona;

import mx.edu.utez.proyecto1D.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.repository.persona.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Transactional(readOnly = true)
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("Persona no encontrada con el id: " + id));
    }

    @Transactional
    public Persona actualizarPersona(Long id, RequestUpdatePersonaDTO dto) {
        Persona personaExistente = obtenerPorId(id);

        personaExistente.setNombre(dto.getNombre());
        personaExistente.setPrimerApellido(dto.getPrimerApellido());
        personaExistente.setSegundoApellido(dto.getSegundoApellido());
        personaExistente.setFechaNacimiento(dto.getFechaNacimiento());
        personaExistente.setCurp(dto.getCurp());
        personaExistente.getCorreo();
        personaExistente.setCorreo(dto.getCorreo());

        return personaRepository.save(personaExistente);
    }
}