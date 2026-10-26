package mx.edu.utez.proyecto1D.service;

import mx.edu.utez.proyecto1D.controller.dto.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1D.controller.dto.ResponseCalculaoraDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public ResponseCalculaoraDTO calculadora (RequestCalculadoraDTO data){
        ResponseCalculaoraDTO respuesta = new ResponseCalculaoraDTO();

        if(
                !data.getOperacion().equals("SUMA") &&
                        !data.getOperacion().equals("RESTA") &&
                        !data.getOperacion().equals("MULTIPLICACION") &&
                        !data.getOperacion().equals("DIVISION")
                ){
            throw new CustomBadRequestException("La operacion solicitada no es valida," +
                    " debe ser SUMA, RESTA, MULTIPLICACION O DIVISION");
        }


        switch (data.getOperacion()){
            case "SUMA":
                respuesta.setResultado(
                        data.getNum1() + data.getNum2()
                );
                break;
            case "RESTA":
                respuesta.setResultado(
                        data.getNum1() - data.getNum2()
                );
                break;
            case "MULTIPLICACION":
                respuesta.setResultado(
                        data.getNum1() * data.getNum2()
                );
                break;
            case "DIVISION":
                respuesta.setResultado(
                        data.getNum1() / data.getNum2()
                );
                break;
        }
         respuesta.setOperacionRealizada(data.getOperacion());

        return respuesta;
    }
}
