package Senet.Ramboot.entity;


import Senet.Ramboot.Enum.EstadoOrdenador;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenador")
public class OrdenadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "ip_local")
    private String ipAddress;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoOrdenador estado = EstadoOrdenador.LIBRE;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public OrdenadorEntity() {
    }

    public OrdenadorEntity(String nombre, String macAddress,EstadoOrdenador estado, String ipAddress, UsuarioEntity usuario) {
        this.nombre = nombre;
        this.macAddress = macAddress;
        this.estado = estado;
        this.ipAddress = ipAddress;
        this.usuario = usuario;
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
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public EstadoOrdenador getEstado() {
        return estado;
    }
    public void setEstado(EstadoOrdenador estado) {
        this.estado = estado;
    }
    public UsuarioEntity getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}
