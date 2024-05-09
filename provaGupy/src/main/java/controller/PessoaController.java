package controller;

import entity.PessoaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaEntity> criarPessoa(@RequestBody PessoaEntity pessoa) {
        PessoaEntity novaPessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaEntity> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaEntity pessoa) {
        PessoaEntity pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoa);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEntity> consultarPessoa(@PathVariable Long id) {
        PessoaEntity pessoa = pessoaService.consultarPessoa(id);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
