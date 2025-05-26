package Senet.Ramboot.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Senet.Ramboot.entity.OrdenadorEntity;


@Repository
public interface OrdenadorRepository extends JpaRepository<OrdenadorEntity, Long> {

    Page<OrdenadorEntity> findById(Long id_usuario, Pageable pageable);

    
    
}
