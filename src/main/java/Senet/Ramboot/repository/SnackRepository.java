package Senet.Ramboot.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Senet.Ramboot.entity.SnackEntity;

import java.util.Optional;

@Repository
public interface SnackRepository  extends JpaRepository<SnackEntity, Long> {
    
    Optional<SnackEntity> findById(Long id);
    
    @SuppressWarnings("null")
    Page<SnackEntity> findAll(Pageable oPageable);
    
    Page<SnackEntity> findByNombreContaining(String nombre, Pageable oPageable);
    
//    Page<SnackEntity> findByPrecioUnidadBetween(BigDecimal precioMin, BigDecimal precioMax, Pageable oPageable);
}
