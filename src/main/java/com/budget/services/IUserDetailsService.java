package com.budget.services;

import com.budget.dao.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by home on 14.11.16.
 */
public interface IUserDetailsService extends UserDetailsService {
    void changeUsername(User user, String password);
}
