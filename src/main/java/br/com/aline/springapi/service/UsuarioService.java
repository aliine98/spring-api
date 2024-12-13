package br.com.aline.springapi.service;

import br.com.aline.springapi.exception.BusinessException;
import br.com.aline.springapi.model.Usuario;
import br.com.aline.springapi.model.UsuarioDTO;
import br.com.aline.springapi.model.UsuarioLoginDTO;
import br.com.aline.springapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    public static final String ID_BAD_FORMAT = "Não foi possível identificar o formato do id. Insira um id válido!";
    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario getUsuario(UsuarioLoginDTO user) {
        Usuario usuarioRes = usuarioRepository.findByLoginAndSenha(user.getLogin(), user.getSenha());

        if(usuarioRes == null) {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        }

        return usuarioRes;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(String id) {
        if(id.matches("\\D")) {
            throw new BusinessException(ID_BAD_FORMAT);
        }

        Integer numId = Integer.parseInt(id);

        Optional<Usuario> usuarioRes = usuarioRepository.findById(numId);

        if (usuarioRes.isPresent()) {
            return usuarioRes.get();
        } else {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        }
    }

    public void cadastraUsuario(UsuarioDTO user) {
        usuarioRepository.save(user.mapToUsuario());
    }

    public void atualizaUsuario(String id, UsuarioDTO user) {
        if(id.matches("\\D")) {
            throw new BusinessException(ID_BAD_FORMAT);
        }

        Integer numId = Integer.parseInt(id);
        Optional<Usuario> userRes = usuarioRepository.findById(numId);

        if(userRes.isPresent()) {
            userRes.get().setSenha(user.getSenha());
            userRes.get().setNome(user.getNome());
            userRes.get().setLogin(user.getLogin());
            usuarioRepository.save(userRes.get());
        } else {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        }
    }

    public void deletaUsuario(String id) {
        if(id.matches("\\D")) {
            throw new BusinessException(ID_BAD_FORMAT);
        }

        Integer numId = Integer.parseInt(id);
        usuarioRepository.deleteById(numId);
    }
}
