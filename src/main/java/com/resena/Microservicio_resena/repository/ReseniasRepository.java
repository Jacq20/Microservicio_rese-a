package com.resena.Microservicio_resena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resena.Microservicio_resena.model.Resenias;

@Repository
public interface ReseniasRepository extends JpaRepository<Resenias, Long> {
    List<Resenias> findByIdProducto(String idProducto);
    boolean existsByIdUsuarioAndIdProducto(String idUsuario, String idProducto);
}
