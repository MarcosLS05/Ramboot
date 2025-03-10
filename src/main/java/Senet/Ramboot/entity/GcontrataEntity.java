package Senet.Ramboot.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gcontrata")
public class GcontrataEntity {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "creado_en")
    private Timestamp fecha_creacion;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_zona")
    private ZonaEntity zona;

    public GcontrataEntity() {
    }

    public GcontrataEntity(UsuarioEntity usuario, ZonaEntity zona) {
        this.usuario = usuario;
        this.zona = zona;
    }

    public GcontrataEntity(Long id, Timestamp fecha_creacion, UsuarioEntity usuario, ZonaEntity zona) {
        this.id = id;
        this.fecha_creacion = fecha_creacion;
        this.usuario = usuario;
        this.zona = zona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ZonaEntity getZona() {
        return zona;
    }

    public void setZona(ZonaEntity zona) {
        this.zona = zona;
    }

    


}
