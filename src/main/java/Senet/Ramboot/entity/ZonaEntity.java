package Senet.Ramboot.entity;

import java.math.BigDecimal;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "zona")
public class ZonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;

    @NotNull
    private BigDecimal precioHora;

    @OneToMany(mappedBy = "zona", fetch = FetchType.LAZY)
    private java.util.List<BonosEntity> bonos = new java.util.ArrayList<>();


    public ZonaEntity() {
    }

    public ZonaEntity(String titulo, BigDecimal precioHora) {
        this.titulo = titulo;
        this.precioHora = precioHora;
    }

    public ZonaEntity(Long id, String titulo, BigDecimal precioHora) {
        this.id = id;
        this.titulo = titulo;
        this.precioHora = precioHora;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPrecio() {
        return precioHora;
    }

    public void setPrecio(BigDecimal precioHora) {    
        this.precioHora = precioHora;
    }
}
