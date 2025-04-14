package Senet.Ramboot.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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

@Entity
@Table(name = "gcontrata")
public class GcontrataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "creado_en")
    private Timestamp fecha_creacion;
 
    
    private BigDecimal importe;  

    @Column(nullable = false, name = "metodo_pago")
    private String metodoPago;

    @Column(nullable = false)
    private String ticket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_zona")
    private ZonaEntity zona;

    @OneToMany(mappedBy = "gcontrata", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GcontrataproductoEntity> gcontrataproductos;

    // Constructores
    public GcontrataEntity() {}

    public GcontrataEntity(String metodoPago,BigDecimal importe, String ticket, UsuarioEntity usuario, ZonaEntity zona) {
        this.metodoPago = metodoPago;
        this.importe = importe;
        this.ticket = ticket;
        this.usuario = usuario;
        this.zona = zona;
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

    public ZonaEntity getZona() { return zona; }
    public void setZona(ZonaEntity zona) { this.zona = zona; }

    public List<GcontrataproductoEntity> getGcontrataproductos() { return gcontrataproductos; }
    public void setGcontrataproductos(List<GcontrataproductoEntity> gcontrataproductos) {
        this.gcontrataproductos = gcontrataproductos;
    }
}
