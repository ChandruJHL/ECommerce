package com.educative.ecommerce.repository;

import java.util.Optional;
import com.educative.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

}