package mx.edu.utez.proyecto1D.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCalculadoraDTO {
    private int num1;
    private int num2;

    @NotBlank(message = "La operacion es obligatoria")
    private String operacion;
}
