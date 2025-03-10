package com.ElectronicStore.service;

import com.ElectronicStore.entity.Account;
import com.ElectronicStore.repositories.AccountRepository;
import com.ElectronicStore.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private AccountRepository accountRepository;
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> user = accountRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Account u = user.get();
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), u.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails) {
            return ((org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }

    public Account getCurrentAccount() {
        return accountRepository.findByUsername(getCurrentUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}