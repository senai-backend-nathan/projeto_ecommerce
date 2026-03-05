package br.com.senai.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.e_commerce.entity.Pedido;
import br.com.senai.e_commerce.exception.Response;
import br.com.senai.e_commerce.repository.PedidoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Pedido")
public class PedidoController {
      @Autowired
    private PedidoRepository repository;

    @PostMapping
    public Response createPedido(@Valid @RequestBody Pedido pedido) {

        repository.save(pedido);
        return new Response(201, "Pedido criada com sucesso");
//201 Created : recurso criado com sucesso
    }

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return repository.findAll();

    }

    @PutMapping("{id}")
    public Response updatePedido(@PathVariable Long id, @RequestBody Pedido updated){
        if (!repository.existsById(id)) {
            return new Response(404, "Pedido não encontrada");
//404 Not Found: recurso não encontrado 
        }
        Pedido pedido = repository.findById(id).get();

        if (updated.getData() != null) {
            pedido.setData(updated.getData());
        }
        if (updated.getQuantidade() != null) {
            pedido.setQuantidade(updated.getQuantidade());
        }
         if (updated.getStatus() != null) {
            pedido.setStatus(updated.getStatus());
        }
        

        repository.save(pedido);

        return new Response(200, "Pedido atualizada com sucesso");
 //200 OK: requisição bem sucedida 

    }


    @DeleteMapping("{id}")
    public Response deletePedido(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Pedido não encontrada");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Pedido deletada com successo");
    }
    
}
