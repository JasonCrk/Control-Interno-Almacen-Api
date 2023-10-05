package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.ActaInventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActaInventarioRepository extends JpaRepository<ActaInventario, Long> {
}
