package br.com.fiap.fordpublisher.controller;

import br.com.fiap.fordpublisher.model.Veiculo;
import br.com.fiap.fordpublisher.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Veiculo> salvar(
            @RequestBody Veiculo veiculo
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(veiculo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(
            @PathVariable Long id,
            @RequestBody Veiculo veiculo
    ) {
        return ResponseEntity.ok(service.atualizar(id, veiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}