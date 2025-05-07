package Senet.Ramboot.DTO;


import java.util.List;
import Senet.Ramboot.entity.GcontrataEntity;
import Senet.Ramboot.entity.GcontrataproductoEntity;

public class CompraRequest {

    private GcontrataEntity gcontrata;
    private List<GcontrataproductoEntity> productos;

    // Getters y setters
    public GcontrataEntity getGcontrata() {
        return gcontrata;
    }

    public void setGcontrata(GcontrataEntity gcontrata) {
        this.gcontrata = gcontrata;
    }

    public List<GcontrataproductoEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<GcontrataproductoEntity> productos) {
        this.productos = productos;
    }
}

