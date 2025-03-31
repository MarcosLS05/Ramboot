package Senet.Ramboot.entity;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "tipousuario")
public class TipousuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)    
    @Size(min = 3, max = 255)
    private String titulo;

    @OneToMany(mappedBy = "tipousuario", fetch = FetchType.LAZY)
    private List<UsuarioEntity> usuarios = new ArrayList<>();


    public TipousuarioEntity() {
    }

    public TipousuarioEntity(String titulo) {
        this.titulo = titulo;
    }

    public TipousuarioEntity(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
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

    public int getUsuarios() {
        return usuarios.size();
    }
}