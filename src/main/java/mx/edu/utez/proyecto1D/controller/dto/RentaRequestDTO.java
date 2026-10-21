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
public class RentaRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    private String tipoVehiculo;

    @NotNull(message = "El número de días de renta es obligatorio")
    @Min(value = 1, message = "Los días de renta deben ser al menos 1")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @Min(value = 0, message = "Los kilómetros no pueden ser negativos")
    private Integer kilometrosEstimados;

    @NotNull(message = "La opción de seguro completo es obligatoria")
    private Boolean seguroCompleto;
}