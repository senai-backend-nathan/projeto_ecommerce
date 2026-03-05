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

import br.com.senai.e_commerce.entity.Produto;
import br.com.senai.e_commerce.exception.Response;
import br.com.senai.e_commerce.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Produto")
public class ProdutoController {
     @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public Response createProduto(@Valid @RequestBody Produto produto) {

        repository.save(produto);
        return new Response(201, "Produto criada com sucesso");
        //201 Created : recurso criado com sucesso
    }

    @GetMapping
    public List<Produto> getAllProdutos() {
        return repository.findAll();

    }

    @PutMapping("{id}")
    public Response updateProduto(@PathVariable Long id, @RequestBody Produto updated){
        if (!repository.existsById(id)) {
            return new Response(404, "Produto não encontrado");
//404 Not Found: recurso não encontrado 
        }
        Produto produto = repository.findById(id).get();

        if (updated.getNome() != null) {
            produto.setNome(updated.getNome());
        }
        if (updated.getPreco() != null) {
            produto.setPreco(updated.getPreco());
        }
        

        repository.save(produto);

        return new Response(200, "Produto atualizada com sucesso");
    //200 OK: requisição bem sucedida 

    }


    @DeleteMapping("{id}")
    public Response deleteProduto(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Produto não encontrada");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Produto deletada com successo");
    }
    
}
