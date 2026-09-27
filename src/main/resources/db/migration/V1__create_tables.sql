CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE veiculo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    placa VARCHAR(20) NOT NULL,
    ano INT NOT NULL,
    quilometragem INT NOT NULL,
    cliente_id BIGINT,
    CONSTRAINT fk_veiculo_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE servico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    valor DOUBLE NOT NULL,
    data_servico DATE NOT NULL,
    veiculo_id BIGINT,
    CONSTRAINT fk_servico_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);