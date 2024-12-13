package br.com.aline.springapi.controller;

import br.com.aline.springapi.model.Usuario;
import br.com.aline.springapi.model.UsuarioLoginDTO;
import br.com.aline.springapi.model.UsuarioDTO;
import br.com.aline.springapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> listaDeUsuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(listaDeUsuarios);
    }

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> getUsuario(@Valid @RequestBody UsuarioLoginDTO user) {
        Usuario usuarioEncontrado = usuarioService.getUsuario(user);
        return ResponseEntity.ok(usuarioEncontrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsusarioById(@PathVariable("id") String id) {
        Usuario usuarioEncontrado = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuarioEncontrado);
    }

    @PostMapping("/register")
    public ResponseEntity<String> cadastraUsuario(@Valid @RequestBody UsuarioDTO user) {
        usuarioService.cadastraUsuario(user);
        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizaUsuario(@PathVariable("id") String id,@Valid @RequestBody UsuarioDTO user) {
        usuarioService.atualizaUsuario(id, user);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaUsuario(@PathVariable("id") String id) {
        usuarioService.deletaUsuario(id);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}
