package service;

import entity.PessoaEntity;
import exception.PessoaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaEntity criarPessoa(PessoaEntity pessoa) {
        validarCamposObrigatorios(pessoa);
        return pessoaRepository.save(pessoa);
    }

    public PessoaEntity atualizarPessoa(Long id, PessoaEntity pessoaAtualizada) {
        PessoaEntity pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa não encontrada"));

        validarCamposObrigatorios(pessoaAtualizada);

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setEnderecos(pessoaAtualizada.getEnderecos());
        pessoaExistente.setEnderecoPrincipal(pessoaAtualizada.getEnderecoPrincipal());

        return pessoaRepository.save(pessoaExistente);
    }

    public PessoaEntity consultarPessoa(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa não encontrada"));
    }

    private void validarCamposObrigatorios(PessoaEntity pessoa) {
        if (pessoa.getNome() == null || pessoa.getDataNascimento() == null
                || pessoa.getEnderecos() == null || pessoa.getEnderecos().isEmpty()
                || pessoa.getEnderecoPrincipal() == null) {
            throw new IllegalArgumentException("Os campos obrigatórios não podem ser nulos");
        }
    }
}
