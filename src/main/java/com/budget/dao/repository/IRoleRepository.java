package com.budget.dao.repository;

import com.budget.dao.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by home on 14.11.16.
 */
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
