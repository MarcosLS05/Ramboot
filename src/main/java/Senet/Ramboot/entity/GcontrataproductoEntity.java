package Senet.Ramboot.entity;


import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "gcontrata_producto")
public class GcontrataproductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    private BigDecimal importe;  


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gcontrata")
    private GcontrataEntity gcontrata;


    
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private ProductoEntity producto;

    // Constructores
    public GcontrataproductoEntity() {}

    public GcontrataproductoEntity(Long id, int cantidad,BigDecimal importe, GcontrataEntity gcontrata, ProductoEntity producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.importe = importe;
        this.gcontrata = gcontrata;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public GcontrataEntity getGcontrata() { return gcontrata; }
    public void setGcontrata(GcontrataEntity gcontrata) { this.gcontrata = gcontrata; }

    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }


public ProductoEntity getProducto() {
    return producto;
}

public void setProducto(ProductoEntity producto) {
    this.producto = producto;
}
}
