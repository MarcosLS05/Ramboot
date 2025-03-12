package Senet.Ramboot.entity;

import java.math.BigDecimal;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bonos")
public class BonoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 255)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_zona")
    private ZonaEntity zona;  // Cambié "ZonaEntity" a "zona" para seguir la convención de nombres

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_bebida")
    private BebidaEntity bebida;
    
    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_snack")
    private SnackEntity snack;

    public BonoEntity() {
    }
    public BonoEntity(String nombre, BigDecimal precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public BonoEntity(Long id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;    
        this.precio = precio;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public ZonaEntity getZonaEntity() {
        return zona;
    }
    public void setZonaEntity(ZonaEntity zonaEntity) {
        zona = zonaEntity;
    }

    
}
