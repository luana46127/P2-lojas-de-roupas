package br.edu.fiec.lojaDeRoupas.service;

import br.edu.fiec.lojaDeRoupas.model.dto.ClienteDTO;
import br.edu.fiec.lojaDeRoupas.model.entity.Cliente;
import br.edu.fiec.lojaDeRoupas.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    public void cadastrarCliente(ClienteDTO clienteDTO) {
        clienteRepository.save(new Cliente(clienteDTO));
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElse(null);
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email).orElse(null);
    }

    public void apagar(Integer id) {
        clienteRepository.deleteById(id);
    }

    public void atualizar(Integer id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente != null) {
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setEndereco(clienteDTO.getEndereco());

            clienteRepository.save(cliente);
        }
    }

}
