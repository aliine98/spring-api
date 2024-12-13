package br.com.aline.springapi.repository;

import br.com.aline.springapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByLoginAndSenha(@Param("login") String login,@Param("senha") String senha);
}
