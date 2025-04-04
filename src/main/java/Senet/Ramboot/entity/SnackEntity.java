package Senet.Ramboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.Size;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "snacks")
public class SnackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 255)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal precioUnidad;

    @Column(nullable = false)
    private int stock;

    @OneToMany(mappedBy = "snack", fetch = FetchType.LAZY)
    private List<BonoEntity> bonos;

    public SnackEntity() {
    }

    public SnackEntity(String nombre, BigDecimal precioUnidad, int stock) {
        this.nombre = nombre;
        this.precioUnidad = precioUnidad;
        this.stock = stock;
    }

    public SnackEntity(Long id, String nombre, BigDecimal precioUnidad, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnidad = precioUnidad;
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
        return precioUnidad;
    }

    public void setPrecio(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
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
}
