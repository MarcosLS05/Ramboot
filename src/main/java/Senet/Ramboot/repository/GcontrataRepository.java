package Senet.Ramboot.repository;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Senet.Ramboot.entity.GcontrataEntity;

@Repository
public interface GcontrataRepository extends JpaRepository<GcontrataEntity, Long> {
    
    Optional <GcontrataEntity> findById(Long id);
    Page<GcontrataEntity> findAll(Pageable oPageable);
    Page<GcontrataEntity> findByTicketContaining(String ticket, Pageable oPageable);
}
