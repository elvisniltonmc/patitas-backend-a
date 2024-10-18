package pe.edu.cibertec.patitas_backend_a.service;

import pe.edu.cibertec.patitas_backend_a.dto.CloseRequest;
import pe.edu.cibertec.patitas_backend_a.dto.LoginRequestDTO;

import java.io.IOException;

public interface AutenticacionService {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;
    void cerrarSesion(CloseRequest request) throws IOException;

}
