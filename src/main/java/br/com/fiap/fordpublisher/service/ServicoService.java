package br.com.fiap.fordpublisher.service;

import br.com.fiap.fordpublisher.exception.ResourceNotFoundException;
import br.com.fiap.fordpublisher.model.Servico;
import br.com.fiap.fordpublisher.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public List<Servico> listarTodos() {
        return repository.findAll();
    }

    public Servico salvar(Servico servico) {
        return repository.save(servico);
    }

    public Servico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Serviço não encontrado"));
    }

    public Servico atualizar(Long id, Servico servicoAtualizado) {
        Servico servico = buscarPorId(id);

        servico.setDescricao(servicoAtualizado.getDescricao());
        servico.setStatus(servicoAtualizado.getStatus());
        servico.setValor(servicoAtualizado.getValor());
        servico.setDataServico(servicoAtualizado.getDataServico());
        servico.setVeiculo(servicoAtualizado.getVeiculo());

        return repository.save(servico);
    }

    public void deletar(Long id) {
        Servico servico = buscarPorId(id);
        repository.delete(servico);
    }
}