package mx.edu.utez.proyecto1D.service;

import mx.edu.utez.proyecto1D.controller.dto.RentaRequestDTO;
import mx.edu.utez.proyecto1D.controller.dto.RentaResponseDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public RentaResponseDTO calcularRenta(RentaRequestDTO data) {
        String tipoVehiculoUpper = data.getTipoVehiculo().toUpperCase();
        int edad = data.getEdadConductor();
        int dias = data.getDiasRenta();
        int kmEstimados = data.getKilometrosEstimados();

        if (edad < 18) {
            throw new CustomBadRequestException("El conductor debe tener al menos 18 años de edad");
        }
        if (dias > 30) {
            throw new CustomBadRequestException("La renta no puede superar los 30 días");
        }
        if (kmEstimados > 5000) {
            throw new CustomBadRequestException("Los kilómetros estimados no pueden superar los 5,000 km");
        }
        if (tipoVehiculoUpper.equals("CAMIONETA") && edad < 25) {
            throw new CustomBadRequestException("Para rentar una CAMIONETA el conductor debe tener al menos 25 años");
        }

        double costoDiario;
        switch (tipoVehiculoUpper) {
            case "COMPACTO": costoDiario = 550.0; break;
            case "SEDAN": costoDiario = 700.0; break;
            case "SUV": costoDiario = 950.0; break;
            case "CAMIONETA": costoDiario = 1200.0; break;
            default:
                throw new CustomBadRequestException("Tipo de vehículo no válido. Permitidos: COMPACTO, SEDAN, SUV, CAMIONETA");
        }

        double costoRenta = costoDiario * dias;

        if (dias >= 7) {
            costoRenta -= costoRenta * 0.10;
        }

        int kmIncluidos = dias * 100;
        double cargoKmAdicionales = 0.0;
        if (kmEstimados > kmIncluidos) {
            cargoKmAdicionales = (kmEstimados - kmIncluidos) * 4.0;
        }

          double cargoEdad = 0.0;
        if (edad >= 18 && edad <= 24) {
            cargoEdad = (costoRenta + cargoKmAdicionales) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(data.getSeguroCompleto())) {
            cargoSeguro = dias * 180.0;
        }

        double total = costoRenta + cargoKmAdicionales + cargoEdad + cargoSeguro;

        return new RentaResponseDTO(data.getNombreCliente(), total, "Cotización de renta calculada exitosamente");
    }
}