package mx.edu.utez.usuarios_tickets.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateUsuarioDTO {

    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    private String nombre;
}