package com.ElectronicStore.service;


import com.ElectronicStore.entity.Account;
import com.ElectronicStore.repositories.AccountRepository;
import com.ElectronicStore.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        UserDetails userDetails = new User(
                "customer",
                "password123",
                List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
        );
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        Account account = new Account();
        account.setUsername("testuser");
        account.setPassword("password");
        account.setRoles(Set.of("ROLE_CUSTOMER"));
        when(accountRepository.findByUsername("testuser")).thenReturn(Optional.of(account));

        var userDetails = accountService.loadUserByUsername("testuser");

        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(accountRepository.findByUsername("unknown")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> accountService.loadUserByUsername("unknown"));
    }

    @Test
    public void testGetCurrentAccount_UserExists() {
        Account account = new Account();
        account.setUsername("testuser");

        when(accountRepository.findByUsername(anyString())).thenReturn(Optional.of(account));

        var currentAccount = accountService.getCurrentAccount();

        assertNotNull(currentAccount);
        assertEquals("testuser", currentAccount.getUsername());
    }

    @Test
    public void testGetCurrentAccount_UserNotFound() {
        when(accountRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> accountService.getCurrentAccount());
    }
}