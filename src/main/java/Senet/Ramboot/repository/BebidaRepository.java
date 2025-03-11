package Senet.Ramboot.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Senet.Ramboot.entity.BebidaEntity;

import java.util.Optional;

public interface BebidaRepository extends JpaRepository<BebidaEntity, Long> {
    
    Optional<BebidaEntity> findById(Long id);

    Optional<BebidaEntity> findByNombre(String nombre);
}
