package com.dmitrij.restapi_jwt.service;

import com.dmitrij.restapi_jwt.entities.User;
import com.dmitrij.restapi_jwt.repositories.RoleRepository;
import com.dmitrij.restapi_jwt.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(
                String.format("user '%s' not found", userName)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }

    public  void createNewUserForPosts(User user) {
        user.setRoles(List.of(roleRepository.findByName("ROLE_POSTS").get()));
        userRepository.save(user);
    }

    public  void createNewUserForAlbums(User user) {
        user.setRoles(List.of(roleRepository.findByName("ROLE_ALBUMS").get()));
        userRepository.save(user);
    }

    public  void createNewUserForUsers(User user) {
        user.setRoles(List.of(roleRepository.findByName("ROLE_USERS").get()));
        userRepository.save(user);
    }
}

