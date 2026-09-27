package br.com.fiap.fordpublisher.service;

import br.com.fiap.fordpublisher.exception.ResourceNotFoundException;
import br.com.fiap.fordpublisher.model.Veiculo;
import br.com.fiap.fordpublisher.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Veiculo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Veículo não encontrado"));
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculo = buscarPorId(id);

        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setPlaca(veiculoAtualizado.getPlaca());
        veiculo.setAno(veiculoAtualizado.getAno());
        veiculo.setQuilometragem(veiculoAtualizado.getQuilometragem());
        veiculo.setCliente(veiculoAtualizado.getCliente());

        return repository.save(veiculo);
    }

    public void deletar(Long id) {
        Veiculo veiculo = buscarPorId(id);
        repository.delete(veiculo);
    }
}