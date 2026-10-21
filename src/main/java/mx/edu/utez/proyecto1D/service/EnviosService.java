package mx.edu.utez.proyecto1D.service;

import mx.edu.utez.proyecto1D.controller.dto.EnviosRequestDTO;
import mx.edu.utez.proyecto1D.controller.dto.EnviosResponseDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class EnviosService {

    public EnviosResponseDTO calcularCotizacion(EnviosRequestDTO data) {
        String tipoEnvioUpper = data.getTipoEnvio().toUpperCase();


        if (!tipoEnvioUpper.equals("ESTANDAR") &&
                !tipoEnvioUpper.equals("EXPRESS") &&
                !tipoEnvioUpper.equals("MISMO_DIA")) {
            throw new CustomBadRequestException("El tipo de envío no es válido. Debe ser ESTANDAR, EXPRESS o MISMO_DIA");
        }

        double peso = data.getPesoKg();
        double largo = data.getLargoCm();
        double ancho = data.getAnchoCm();
        double alto = data.getAltoCm();
        double volumen = largo * ancho * alto;


        if (peso > 50) {
            throw new CustomBadRequestException("El paquete supera el peso máximo permitido (50 kg)");
        }
        if (largo > 150 || ancho > 150 || alto > 150) {
            throw new CustomBadRequestException("Ninguna dimensión del paquete puede ser superior a 150 cm");
        }
        if (volumen > 1000000) {
            throw new CustomBadRequestException("El volumen del paquete no puede ser superior a 1,000,000 cm³");
        }


        double costoTotal = 80.0;
        costoTotal += peso * 12.0;

        if (volumen > 50000) {
            costoTotal += 100.0;
        }

        if (tipoEnvioUpper.equals("EXPRESS")) {
            costoTotal += costoTotal * 0.40;
        } else if (tipoEnvioUpper.equals("MISMO_DIA")) {
            costoTotal += costoTotal * 0.70;
        }

        if (data.getValorDeclarado() > 10000) {
            costoTotal += data.getValorDeclarado() * 0.02;
        }

        return new EnviosResponseDTO(costoTotal, tipoEnvioUpper, "Cotización de envío calculada exitosamente");
    }
}