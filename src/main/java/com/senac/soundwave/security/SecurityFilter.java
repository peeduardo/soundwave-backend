package com.senac.soundwave.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.senac.soundwave.repository.UserRepository;
import com.senac.soundwave.user.User;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getServletPath();

        System.out.println(path);
        // 🔥 IGNORA TODAS ESSAS ROTAS (não validar token nelas!)
        if (path.startsWith("/auth/")
                || path.startsWith("/musicas/")
                || path.startsWith("/uploads/")
                || path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.equals("/")
                || request.getMethod().equals("OPTIONS")) {

            filterChain.doFilter(request, response);
            return;
        }
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            User user = userRepository.findById(Integer.valueOf(login))
                    .orElseThrow(() -> new RuntimeException("User Not Found"));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }
}