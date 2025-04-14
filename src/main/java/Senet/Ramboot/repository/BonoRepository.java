package Senet.Ramboot.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import Senet.Ramboot.entity.BonoEntity;

@Repository
public interface BonoRepository extends JpaRepository<BonoEntity, Long> {

    @SuppressWarnings("null")
    Optional<BonoEntity> findById(Long id);

    Page<BonoEntity> findByNombreContaining(String nombre, Pageable oPageable);
    
}
