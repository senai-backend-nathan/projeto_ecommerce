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

import br.com.senai.e_commerce.entity.Categoria;
import br.com.senai.e_commerce.exception.Response;
import br.com.senai.e_commerce.repository.CategoriaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Categoria")
public class CategoriaController {
     @Autowired
    private CategoriaRepository repository;

    @PostMapping
    public Response createCategoria(@Valid @RequestBody Categoria categoria) {

        repository.save(categoria);
        return new Response(201, "Categoria criada com sucesso");
        //201 Created : recurso criado com sucesso
    }

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return repository.findAll();

    }

    @PutMapping("{id}")
    public Response updateCategoria(@PathVariable Long id, @RequestBody Categoria updated){
        if (!repository.existsById(id)) {
            return new Response(404, "Categoria não encontrada");
//404 Not Found: recurso não encontrado 
        }
        Categoria categoria = repository.findById(id).get();

        if (updated.getNome() != null) {
            categoria.setNome(updated.getNome());
        }
        if (updated.getDescricao() != null) {
            categoria.setDescricao(updated.getDescricao());
        }
        

        repository.save(categoria);

        return new Response(200, "Categoria atualizada com sucesso");
         //200 OK: requisição bem sucedida 

    }


    @DeleteMapping("{id}")
    public Response deleteCategoria(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Categoria não encontrada");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Categoria deletada com successo");
    }
}
