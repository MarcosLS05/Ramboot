package Senet.Ramboot.repository;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Senet.Ramboot.entity.GcontrataEntity;

@Repository
public interface GcontrataRepository extends JpaRepository<GcontrataEntity, Long> {
    
    Optional <GcontrataEntity> findById(Long id);

    Page<GcontrataEntity> findAll(Pageable oPageable);

    Page<GcontrataEntity> findByTicketContaining(String ticket, Pageable oPageable);

    Page<GcontrataEntity> findByUsuarioId(Long idUsuario, Pageable pageable);

        @Query("SELECT g FROM GcontrataEntity g LEFT JOIN FETCH g.gcontrataproducto WHERE g.id = :id")
        Optional<GcontrataEntity> findByIdWithProductos(@Param("id") Long id);


    @Query(value = "SELECT * FROM gcontrata WHERE id_usuario = :idUsuario AND (:filter IS NULL OR ticket LIKE %:filter%) ORDER BY creado_en ASC", nativeQuery = true)
    Page<GcontrataEntity> findByUsuarioIdAndFilter(
            @Param("idUsuario") Long idUsuario,
            @Param("filter") String filter,
            Pageable pageable
    );

   @Query(value = "SELECT g.* FROM gcontrata g " +
               "JOIN gcontrata_producto gp ON g.id = gp.id_gcontrata " +
               "WHERE gp.id_gcontrataproducto = :idGcontrataproducto", nativeQuery = true)
Page<GcontrataEntity> findByIdGcontrataproducto(@Param("idGcontrataproducto") Long idGcontrataproducto, Pageable pageable);
}
