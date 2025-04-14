package Senet.Ramboot.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

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
    private ZonaEntity zona;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;


    public BonoEntity() {
    }

    public BonoEntity(String nombre, BigDecimal precio, ZonaEntity zona, ProductoEntity producto) {
        this.nombre = nombre;
        this.precio = precio;
        this.zona = zona;
        this.producto = producto;

    }

    public BonoEntity(Long id, String nombre, BigDecimal precio, ZonaEntity zona, ProductoEntity producto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.zona = zona;
        this.producto = producto;

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

    public ZonaEntity getZona() {
        return zona;
    }

    public void setZona(ZonaEntity zonaEntity) {
        zona = zonaEntity;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }


}
