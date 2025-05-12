package Senet.Ramboot.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "gcontrata")
public class GcontrataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "creado_en")
    private Timestamp fecha_creacion;
 
    @NotNull    
    private BigDecimal importe;  

    @Column(nullable = false, name = "metodo_pago")
    private String metodoPago;

    @Column(nullable = false)
    private String ticket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;



    @OneToMany(mappedBy = "gcontrata", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GcontrataproductoEntity> gcontrataproducto;
    

    // Constructores
    public GcontrataEntity() {}

    public GcontrataEntity(String metodoPago,BigDecimal importe, String ticket, UsuarioEntity usuario) {
        this.metodoPago = metodoPago;
        this.importe = importe;
        this.ticket = ticket;
        this.usuario = usuario;
        
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Timestamp getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(Timestamp fecha_creacion) { this.fecha_creacion = fecha_creacion; }

    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getTicket() { return ticket; }
    public void setTicket(String ticket) { this.ticket = ticket; }

    public UsuarioEntity getUsuario() { return usuario; }
    public void setUsuario(UsuarioEntity usuario) { this.usuario = usuario; }


    public List<GcontrataproductoEntity> getGcontrataproductos() { return gcontrataproducto; }
    public void setGcontrataproductos(List<GcontrataproductoEntity> gcontrataproducto) {
        this.gcontrataproducto = gcontrataproducto;
    }
}
