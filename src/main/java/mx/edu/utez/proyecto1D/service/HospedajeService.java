package mx.edu.utez.proyecto1D.service;

import mx.edu.utez.proyecto1D.controller.dto.HospedajeRequestDTO;
import mx.edu.utez.proyecto1D.controller.dto.HospedajeResponseDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public HospedajeResponseDTO calcularHospedaje(HospedajeRequestDTO data) {
        String tipoHabitacionUpper = data.getTipoHabitacion().toUpperCase();
        String temporadaUpper = data.getTemporada().toUpperCase();
        int noches = data.getNumeroNoches();
        int huéspedes = data.getNumeroHuespedes();

        if (noches > 30) {
            throw new CustomBadRequestException("El número de noches no puede superar las 30");
        }

        double costoNoche;
        int capacidadMaxima;

        switch (tipoHabitacionUpper) {
            case "INDIVIDUAL":
                costoNoche = 700.0;
                capacidadMaxima = 1;
                break;
            case "DOBLE":
                costoNoche = 1100.0;
                capacidadMaxima = 2;
                break;
            case "SUITE":
                costoNoche = 1800.0;
                capacidadMaxima = 4;
                break;
            default:
                throw new CustomBadRequestException("Tipo de habitación no válido. Permitidos: INDIVIDUAL, DOBLE, SUITE");
        }

        if (huéspedes > capacidadMaxima) {
            throw new CustomBadRequestException("Se supera la capacidad máxima de la habitación " + tipoHabitacionUpper + " (" + capacidadMaxima + " personas)");
        }

        double costoHospedaje = costoNoche * noches;
        if (temporadaUpper.equals("BAJA")) {
            costoHospedaje -= costoHospedaje * 0.10;
        } else if (temporadaUpper.equals("ALTA")) {
            costoHospedaje += costoHospedaje * 0.25;
        } else if (!temporadaUpper.equals("REGULAR")) {
            throw new CustomBadRequestException("La temporada debe ser BAJA, REGULAR o ALTA");
        }

        if (noches >= 7) {
            costoHospedaje -= costoHospedaje * 0.08;
        }

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(data.getIncluyeDesayuno())) {
            costoDesayuno = huéspedes * noches * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(data.getIncluyeEstacionamiento())) {
            costoEstacionamiento = noches * 100.0;
        }

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;


        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        return new HospedajeResponseDTO(
                data.getNombreHuesped(),
                costoHospedaje,
                costoDesayuno,
                costoEstacionamiento,
                subtotal,
                impuesto,
                total,
                "Cotización de hospedaje calculada exitosamente"
        );
    }
}