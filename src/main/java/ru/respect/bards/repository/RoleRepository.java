package ru.respect.bards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.respect.bards.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
