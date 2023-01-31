package com.c0722g1repobe.service.account.impl;

import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.repository.account.IUserRepository;
import com.c0722g1repobe.service.account.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public Optional<Account> findByUsername(String username) {
        return userRepository.findByUsernameAccount(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsernameAccount(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Account save(Account users) {
        return userRepository.save(users);
    }


}
