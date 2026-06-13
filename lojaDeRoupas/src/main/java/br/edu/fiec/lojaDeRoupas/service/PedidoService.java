package br.edu.fiec.lojaDeRoupas.service;

import br.edu.fiec.lojaDeRoupas.model.dto.PedidoDTO;
import br.edu.fiec.lojaDeRoupas.model.entity.Cliente;
import br.edu.fiec.lojaDeRoupas.model.entity.Pedido;
import br.edu.fiec.lojaDeRoupas.model.entity.Produto;
import br.edu.fiec.lojaDeRoupas.repository.ClienteRepository;
import br.edu.fiec.lojaDeRoupas.repository.PedidoRepository;
import br.edu.fiec.lojaDeRoupas.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;

    public void criarPedido(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId()).orElse(null);
        Produto produto = produtoRepository.findById(pedidoDTO.getProdutoId()).orElse(null);

        if (cliente != null && produto != null) {
            // Cria o pedido com o status padrão "PENDENTE"
            pedidoRepository.save(new Pedido("PENDENTE", cliente, produto));
        }
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> buscarPorStatus(String status) {
        return pedidoRepository.findAllByStatus(status);
    }

    public List<Pedido> buscarPorCliente(Integer clienteId) {
        return pedidoRepository.findAllByClienteId(clienteId);
    }

    public void apagar(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public void atualizarStatus(Integer id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setStatus(novoStatus);
            pedidoRepository.save(pedido);
        }
    }


}
