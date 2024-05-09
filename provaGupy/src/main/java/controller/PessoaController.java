package controller;

import entity.PessoaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.PessoaService;

@Controller
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public PessoaEntity criarPessoa(@RequestBody PessoaEntity pessoa) {
        return pessoaService.criarPessoa(pessoa);
    }

    @PutMapping("/{id}")
    public PessoaEntity atualizarPessoa(@PathVariable Long id, @RequestBody PessoaEntity pessoa) {
        return pessoaService.atualizarPessoa(id, pessoa);
    }

    @GetMapping("/{id}")
    public PessoaEntity consultarPessoa(@PathVariable Long id) {
        return pessoaService.consultarPessoa(id);
    }

}
