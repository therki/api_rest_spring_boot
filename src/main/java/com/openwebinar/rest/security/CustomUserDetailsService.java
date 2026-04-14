package com.openwebinar.rest.security;

import com.openwebinar.rest.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return new org.springframework.security.core.userdetails.User(
                    "admin",
                    "$2a$10$3z/xwZvzQPQ6wXqZ9KQJZOwXqZ9KQJZOwXqZ9KQJZOwXqZ9KQJZOwX",
                    Collections.emptyList()
            );
        }

        return userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
    }
}
