package br.com.fiap.fordpublisher.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private String placa;

    private Integer ano;

    private Integer quilometragem;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}