package Senet.Ramboot.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Senet.Ramboot.entity.GcontrataEntity;
import Senet.Ramboot.entity.GcontrataproductoEntity;

public interface GcontrataproductoRepository extends JpaRepository<GcontrataproductoEntity, Long> {
    Page<GcontrataproductoEntity> findByGcontrataContaining(String filter, Pageable oPageable);

   @Query(value = "SELECT g.* FROM gcontrata g " +
               "JOIN gcontrata_producto gp ON g.id = gp.id_gcontrata " +
               "WHERE gp.id_gcontrataproducto = :idGcontrataproducto", nativeQuery = true)
Page<GcontrataEntity> findByIdGcontrataproducto(@Param("idGcontrataproducto") Long idGcontrataproducto, Pageable pageable);
}
