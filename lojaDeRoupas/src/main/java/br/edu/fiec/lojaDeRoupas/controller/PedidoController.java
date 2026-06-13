package br.edu.fiec.lojaDeRoupas.controller;

import br.edu.fiec.lojaDeRoupas.model.dto.PedidoDTO;
import br.edu.fiec.lojaDeRoupas.model.entity.Pedido;
import br.edu.fiec.lojaDeRoupas.service.PedidoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(name = "pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    // Criar um novo pedido
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        pedidoService.criarPedido(pedidoDTO);
    }

    // Retorna todos os pedidos
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<Pedido> buscarTodos() {
        return pedidoService.buscarTodos();
    }

    // Busca 1: Retorna por ID
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}", produces = "application/json")
    public Pedido buscarPorId(@PathVariable Integer id) {
        return pedidoService.buscarPorId(id);
    }

    // Busca 2: Retorna por Status (Ex: PENDENTE, FINALIZADO)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "status", produces = "application/json")
    public List<Pedido> buscarPorStatus(@RequestParam String status) {
        return pedidoService.buscarPorStatus(status);
    }

    // Busca 3: Retorna todos os pedidos de um cliente específico
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "cliente/{clienteId}", produces = "application/json")
    public List<Pedido> buscarPorCliente(@PathVariable Integer clienteId) {
        return pedidoService.buscarPorCliente(clienteId);
    }

    // Atualizar apenas o status do Pedido
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "{id}/status")
    public void atualizarStatus(@PathVariable Integer id, @RequestParam String novoStatus) {
        pedidoService.atualizarStatus(id, novoStatus);
    }

    // Deletar/Cancelar Pedido
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public void apagar(@PathVariable Integer id) {
        pedidoService.apagar(id);
    }
}
