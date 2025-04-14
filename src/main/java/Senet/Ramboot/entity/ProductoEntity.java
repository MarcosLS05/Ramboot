package Senet.Ramboot.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Size(min = 3, max = 255)
    private String nombre;

    
    private BigDecimal precio_unidad;

    
    private int stock;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<BonoEntity> bonos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<GcontrataproductoEntity> gcontrataproductos;

    public ProductoEntity() {
    }

    public ProductoEntity(String nombre, BigDecimal precio_unidad, int stock) {
        this.nombre = nombre;
        this.precio_unidad = precio_unidad;
        this.stock = stock;
    }

    public ProductoEntity(Long id, String nombre, BigDecimal precio_unidad, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio_unidad = precio_unidad;
        this.stock = stock;
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
        return precio_unidad;
    }

    public void setPrecio(BigDecimal precioUnidad) {
        this.precio_unidad = precioUnidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBono() {
        if (bonos == null) {
            return 0;
        } else {
            return bonos.size();
        }
    }

    public int getGcontrataproducto() {
        if (gcontrataproductos == null) {
            return 0;
        } else {
            return gcontrataproductos.size();
        }
    }

    public List<GcontrataproductoEntity> getGcontrataproductos() {
        return gcontrataproductos;
    }
    
    public void setGcontrataproductos(List<GcontrataproductoEntity> gcontrataproductos) {
        this.gcontrataproductos = gcontrataproductos;
    }

}
