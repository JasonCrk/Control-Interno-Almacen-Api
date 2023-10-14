package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.model.enums.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query(value = """
            SELECT u FROM Usuario u WHERE\s
            u.role = :role AND\s
            (UPPER(u.firstName) LIKE UPPER(:searchQuery||'%') OR\s
            UPPER(u.lastName) LIKE UPPER(:searchQuery||'%'))\s
            ORDER BY u.firstName ASC, u.lastName ASC\s
            """)
    List<Usuario> search(
            @Param("role") Role role,
            @Param("searchQuery") String searchQuery
    );
}
