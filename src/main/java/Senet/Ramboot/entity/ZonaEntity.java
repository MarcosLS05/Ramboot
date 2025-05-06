package Senet.Ramboot.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "precio_hora")
    private BigDecimal precioHora;

    @OneToMany(mappedBy = "zona", fetch = FetchType.LAZY)
    private List<BonoEntity> bonos = new ArrayList<>();

    @OneToMany(mappedBy = "zona", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<GcontrataEntity> Gcontrata;

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

    public int getGcontrata() {
        if (Gcontrata == null) {
            return 0;
        } else {
            return Gcontrata.size();
        }

    }

    public int getBono() {
        if (bonos == null) {
            return 0;
        } else {
            return bonos.size();
        }
    }
}
