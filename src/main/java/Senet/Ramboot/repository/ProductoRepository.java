package Senet.Ramboot.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Senet.Ramboot.entity.ProductoEntity;

import java.util.Optional;


@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    
    Optional<ProductoEntity> findById(Long id);

    Optional<ProductoEntity> findByNombreContaining(String nombre);

    Page<ProductoEntity> findByNombreContaining(String nombre, Pageable oPageable);
}
