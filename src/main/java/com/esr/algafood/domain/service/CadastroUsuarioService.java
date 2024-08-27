package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Usuario;
import com.esr.algafood.domain.exception.NOT_FOUND.UsuarioNotFoundException;
import com.esr.algafood.domain.exception.NegocioException;
import com.esr.algafood.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroUsuarioService {

    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if(usuario.senhaNaoCoincideCom(senhaAtual)){
            throw new NegocioException("Senha não coincide com a atual do usuário");
        }

        usuario.setSenha(novaSenha);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));
    }
}
