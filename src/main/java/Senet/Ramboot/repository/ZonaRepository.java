package Senet.Ramboot.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Senet.Ramboot.entity.ZonaEntity;

import java.util.Optional;

public interface ZonaRepository extends JpaRepository<ZonaEntity, Long> {
    
    Optional<ZonaEntity> findById(Long id);
    Page<ZonaEntity> findAll(Pageable oPageable);
    Page<ZonaEntity> findByTituloContaining(String oTitulo, Pageable oPageable);
    
}
