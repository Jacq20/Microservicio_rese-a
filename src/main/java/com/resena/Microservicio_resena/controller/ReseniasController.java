package com.resena.Microservicio_resena.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resena.Microservicio_resena.DTO.ReseniasDTO;
import com.resena.Microservicio_resena.model.Resenias;
import com.resena.Microservicio_resena.service.ReseniasService;

@RestController
@RequestMapping("/api/resenias")
public class ReseniasController {
    
     @Autowired
    private ReseniasService reseniaService;

    @PostMapping
    public ResponseEntity<?> crearResenia(@Valid @RequestBody ReseniasDTO dto) {
        try {
            Resenias nueva = reseniaService.crearResenia(dto);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<?> listarResenias(@PathVariable String idProducto) {
        List<Resenias> lista = reseniaService.listarResenias(idProducto);
        if (lista.isEmpty()) {
            return new ResponseEntity<>("Sin reseñas para este producto",
                HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResenia(@PathVariable Long id) {
        Resenias resenia = reseniaService.buscarPorId(id).orElse(null);
        if (resenia == null) {
            return new ResponseEntity<>("Reseña no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resenia, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarResenia(
            @PathVariable Long id,
            @Valid @RequestBody ReseniasDTO dto) {
        try {
            Resenias editada = reseniaService.editarResenia(id, dto).orElse(null);
            if (editada == null) {
                return new ResponseEntity<>("Reseña no encontrada", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(editada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarResenia(@PathVariable Long id) {
        try {
            boolean eliminada = reseniaService.eliminarResenia(id);
            if (!eliminada) {
                return new ResponseEntity<>("Reseña no encontrada", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Reseña eliminada", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarCompra(
            @RequestParam String idUsuario,
            @RequestParam String idProducto) {
        boolean resultado = reseniaService.verificarCompra(idUsuario, idProducto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
