package com.mecyo.spring.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mecyo.spring.domain.model.Cliente;
import com.mecyo.spring.domain.model.Grupo;
import com.mecyo.spring.domain.model.Permissao;
import com.mecyo.spring.domain.repository.ClienteRepository;
import com.mecyo.spring.domain.repository.GrupoRepository;
import com.mecyo.spring.domain.repository.PermissaoRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository usuarios;

    @Autowired
    private GrupoRepository grupos;

    @Autowired
    private PermissaoRepository permissoes;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> usuario = usuarios.findByEmail(username);

        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        Cliente user = usuario.get();
        
        return new UsuarioSistema(user.getId(), user.getNome(), user.getEmail(), user.getSenha(), authorities(user));
    }

    public Collection<? extends GrantedAuthority> authorities(Cliente usuario) {
        return authorities(grupos.findByUsuariosIn(Arrays.asList(usuario)));
    }

    public Collection<? extends GrantedAuthority> authorities(List<Grupo> grupos) {
        Collection<GrantedAuthority> auths = new ArrayList<>();

        for (Grupo grupo: grupos) {
            List<Permissao> lista = permissoes.findByGruposIn(Arrays.asList(grupo));

            for (Permissao permissao: lista) {
                auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
            }
        }

        return auths;
    }

}
