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
public class EnviosRequestDTO {

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @Min(value = 0, message = "El peso no puede ser negativo")
    private Double pesoKg;

    @NotNull(message = "El largo es obligatorio")
    @Min(value = 0, message = "El largo no puede ser negativo")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @Min(value = 0, message = "El ancho no puede ser negativo")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @Min(value = 0, message = "El alto no puede ser negativo")
    private Double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @Min(value = 0, message = "El valor declarado no puede ser negativo")
    private Double valorDeclarado;
}