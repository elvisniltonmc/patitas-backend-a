package pe.edu.cibertec.patitas_backend_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backend_a.dto.*;
import pe.edu.cibertec.patitas_backend_a.service.AutenticacionService;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        try {
           // Thread.sleep(Duration.ofSeconds(5));
            String[] datosUsuario = autenticacionService.validarUsuario(loginRequestDTO);
            System.out.println("Resultado: " + Arrays.toString(datosUsuario));
            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "");
            }
            return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]);

        } catch (Exception e) {
            return new LoginResponseDTO("99", "Error: Ocurrió un problema", "", "");
        }


    }
    @PostMapping("/close")
    public CloseResponse close(@RequestBody CloseRequest request){
        try{
            Thread.sleep(Duration.ofSeconds(3));
            autenticacionService.cerrarSesion(request);
            return new CloseResponse("00","");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return new CloseResponse("99","Ocurrió un problema en el servidor");
        }
    }
    @PostMapping("/close_ef")
    public ResponseClose2 closeEf(@RequestBody RequestClose2 request) {
        // Validar la entrada
        if (request.numeroDocumento() == null || request.numeroDocumento().isEmpty() ||
                request.tipoDocumento() == null || request.tipoDocumento().isEmpty()) {
            return new ResponseClose2("01", "Falta información para el cierre de sesión");
        }

        try {
            // Simulaando una pausa de 5 segundos
            Thread.sleep(Duration.ofSeconds(5));

            // Llama al servicio para cerrar sesión
            autenticacionService.cerrarSesion2(request);
            return new ResponseClose2("00", "Cierre de sesión exitoso");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return new ResponseClose2("99", "Ocurrió un problema en el servidor");
        }
    }


}
