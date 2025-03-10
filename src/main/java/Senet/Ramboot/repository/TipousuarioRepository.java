package Senet.Ramboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Senet.Ramboot.entity.TipousuarioEntity;


@Repository
public interface TipousuarioRepository extends JpaRepository<TipousuarioEntity, Long> {

    Page<TipousuarioEntity> findByTituloContaining(String filter, Pageable oPageable);

    
}
