package Senet.Ramboot.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import Senet.Ramboot.entity.GcontrataproductoEntity;

public interface GcontrataproductoRepository extends JpaRepository<GcontrataproductoEntity, Long> {
    Page<GcontrataproductoEntity> findByGcontrataContaining(String filter, Pageable oPageable);
}
