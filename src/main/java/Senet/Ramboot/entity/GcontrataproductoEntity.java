package Senet.Ramboot.entity;


import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "gcontrata_producto")
public class GcontrataproductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gcontrata")
    private GcontrataEntity gcontrata;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bebida")
    private BebidaEntity bebida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_snack")
    private SnackEntity snack;

    // Constructores
    public GcontrataproductoEntity() {}

    public GcontrataproductoEntity(Long id, int cantidad, GcontrataEntity gcontrata, BebidaEntity bebida, SnackEntity snack) {
        this.id = id;
        this.cantidad = cantidad;
        this.gcontrata = gcontrata;
        this.bebida = bebida;
        this.snack = snack;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public GcontrataEntity getGcontrata() { return gcontrata; }
    public void setGcontrata(GcontrataEntity gcontrata) { this.gcontrata = gcontrata; }

    public BebidaEntity getBebida() { return bebida; }
    public void setBebida(BebidaEntity bebida) { this.bebida = bebida; }

    public SnackEntity getSnack() { return snack; }
    public void setSnack(SnackEntity snack) { this.snack = snack; }
}
