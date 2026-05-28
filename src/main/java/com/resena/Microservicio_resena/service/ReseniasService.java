package com.resena.Microservicio_resena.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.resena.Microservicio_resena.DTO.ReseniasDTO;
import com.resena.Microservicio_resena.model.Resenias;
import com.resena.Microservicio_resena.model.UsuarioDTO;
import com.resena.Microservicio_resena.repository.ReseniasRepository;

@Service
public class ReseniasService {
    
    @Autowired
    private ReseniasRepository reseniaRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Resenias crearResenia(ReseniasDTO dto) {
        try {
            // Verificamos que el usuario existe en ms_usuarios
            String url = "http://localhost:8089/api/usuarios/" + dto.getIdUsuario();
            UsuarioDTO usuario = restTemplate.getForObject(url, UsuarioDTO.class);

            if (usuario == null) {
                throw new RuntimeException("El usuario no existe");
            }

            Resenias resenia = new Resenias();
            resenia.setIdProducto(dto.getIdProducto());
            resenia.setIdUsuario(dto.getIdUsuario());
            resenia.setCalificacion(dto.getCalificacion());
            resenia.setComentario(dto.getComentario());
            resenia.setCompraVerificada(dto.getCompraVerificada());
            resenia.setFechaCreacion(LocalDateTime.now());
            resenia.setEstadoModeracion(Resenias.EstadoModeracion.PENDIENTE);
            return reseniaRepository.save(resenia);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Resenias> listarResenias(String idProducto) {
        return reseniaRepository.findByIdProducto(idProducto);
    }

    public Optional<Resenias> buscarPorId(Long id) {
        return reseniaRepository.findById(id);
    }

    public Optional<Resenias> editarResenia(Long id, ReseniasDTO dto) {
        try {
            Optional<Resenias> resenia = reseniaRepository.findById(id);
            resenia.ifPresent(r -> {
                r.setComentario(dto.getComentario());
                r.setCalificacion(dto.getCalificacion());
                reseniaRepository.save(r);
            });
            return resenia;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public boolean eliminarResenia(Long id) {
        try {
            if (reseniaRepository.existsById(id)) {
                reseniaRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public boolean verificarCompra(String idUsuario, String idProducto) {
        return reseniaRepository.existsByIdUsuarioAndIdProducto(idUsuario, idProducto);
    }
}
