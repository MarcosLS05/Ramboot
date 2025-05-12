package Senet.Ramboot.DTO;

import java.math.BigDecimal;
import java.util.List;

import Senet.Ramboot.entity.GcontrataEntity;
import Senet.Ramboot.entity.GcontrataproductoEntity;

public class AddimporteRequest {

    private GcontrataEntity gcontrataEntity;  
    private List<GcontrataproductoEntity> productosComprados;  
    private BigDecimal montoParaSaldo;  

    // Getters y Setters
    public GcontrataEntity getGcontrataEntity() {
        return gcontrataEntity;
    }

    public void setGcontrataEntity(GcontrataEntity gcontrataEntity) {
        this.gcontrataEntity = gcontrataEntity;
    }

    public List<GcontrataproductoEntity> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(List<GcontrataproductoEntity> productosComprados) {
        this.productosComprados = productosComprados;
    }

    public BigDecimal getMontoParaSaldo() {
        return montoParaSaldo;
    }

    public void setMontoParaSaldo(BigDecimal montoParaSaldo) {
        this.montoParaSaldo = montoParaSaldo;
    }
}