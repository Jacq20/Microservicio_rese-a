package com.resena.Microservicio_resena.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resenias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResenia;

    @Column(nullable = false)
    private String idProducto;

    @Column(nullable = false)
    private String idUsuario;

    private Integer calificacion;

     @Column(length = 500)
    private String comentario;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoModeracion estadoModeracion;

    private Boolean compraVerificada;

    public enum EstadoModeracion {
        PENDIENTE, APROBADA, RECHAZADA
    }
}
