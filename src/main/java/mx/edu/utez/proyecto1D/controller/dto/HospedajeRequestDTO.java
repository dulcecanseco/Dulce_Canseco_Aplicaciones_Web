package mx.edu.utez.proyecto1D.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospedajeRequestDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Min(value = 1, message = "El número de noches debe ser al menos 1")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 huésped")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    @NotNull(message = "Debe indicar si incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "Debe indicar si incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}