package ru.respect.bards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.respect.bards.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
