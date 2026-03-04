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

import br.com.senai.e_commerce.entity.Avaliacao;
import br.com.senai.e_commerce.exception.Response;
import br.com.senai.e_commerce.repository.AvaliacaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoRepository repository;

    @PostMapping
    public Response createAvaliacao(@Valid @RequestBody Avaliacao avaliacao) {

        repository.save(avaliacao);
        return new Response(201, "Avaliacao criada com sucesso");
        //201 Created : recurso criado com sucesso
    }

    @GetMapping
    public List<Avaliacao> getAllAvaliacaos() {
        return repository.findAll();

    }

    @PutMapping("{id}")
    public Response updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao updated){
        if (!repository.existsById(id)) {
            return new Response(404, "Avaliacao não encontrado");
//404 Not Found: recurso não encontrado 
        }
        Avaliacao avaliacao = repository.findById(id).get();

        if (updated.getNota() != null) {
            avaliacao.setNota(updated.getNota());
        }
        if (updated.getComentario() != null) {
            avaliacao.setComentario(updated.getComentario());
        }
        

        repository.save(avaliacao);

        return new Response(200, "Avaliacao atualizada com sucesso");
         //200 OK: requisição bem sucedida 

    }


    @DeleteMapping("{id}")
    public Response deleteAvaliacao(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Avaliacao não encontrada");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Avaliacao deletada com successo");
    }
}   

