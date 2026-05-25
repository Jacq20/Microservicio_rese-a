package com.resena.Microservicio_resena.DTO;

import org.antlr.v4.runtime.misc.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReseniasDTO {
    
    @NotBlank(message = "El id de producto es obligatorio")
    private String idProducto;

    @NotBlank(message = "El id de usuario es obligatorio")
    private String idUsuario;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "Mínimo 1")
    @Max(value = 5, message = "Máximo 5")
    private Integer calificacion;

    @Size(max = 500, message = "Máximo 500 caracteres")
    private String comentario;

    private Boolean compraVerificada;

}
