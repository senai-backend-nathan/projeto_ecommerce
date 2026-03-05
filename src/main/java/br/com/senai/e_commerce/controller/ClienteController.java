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

import br.com.senai.e_commerce.entity.Cliente;
import br.com.senai.e_commerce.exception.Response;
import br.com.senai.e_commerce.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class ClienteController {
      @Autowired
    private ClienteRepository repository;

    @PostMapping
    public Response createCliente(@Valid @RequestBody Cliente cliente) {

        repository.save(cliente);
        return new Response(201, "Cliente criado com sucesso");
        //201 Created : recurso criado com sucesso
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return repository.findAll();

    }

    @PutMapping("{id}")
    public Response updateCliente(@PathVariable Long id, @RequestBody Cliente updated){
        if (!repository.existsById(id)) {
            return new Response(404, "Cliente não encontrado");
//404 Not Found: recurso não encontrado 
        }
        Cliente cliente = repository.findById(id).get();

        if (updated.getNome() != null) {
            cliente.setNome(updated.getNome());
        }
        if (updated.getEmail() != null) {
            cliente.setEmail(updated.getEmail());
        }
        

        repository.save(cliente);

        return new Response(200, "Cliente atualizado com sucesso");
         //200 OK: requisição bem sucedida 

    }


    @DeleteMapping("{id}")
    public Response deleteCliente(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Cliente não encontrado");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Cliente deletado com successo");
    }
    
}
