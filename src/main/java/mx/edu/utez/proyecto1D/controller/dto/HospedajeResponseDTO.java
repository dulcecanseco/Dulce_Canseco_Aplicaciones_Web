package mx.edu.utez.proyecto1D.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospedajeResponseDTO {
    private String nombreHuesped;
    private double costoHospedaje;
    private double costoDesayuno;
    private double costoEstacionamiento;
    private double subtotal;
    private double impuesto;
    private double total;
    private String mensaje;
}