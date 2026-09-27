package br.com.fiap.fordpublisher.repository;

import br.com.fiap.fordpublisher.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}