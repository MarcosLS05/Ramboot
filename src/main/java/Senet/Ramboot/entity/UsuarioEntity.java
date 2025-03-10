package Senet.Ramboot.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
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
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    @Size(min = 3, max = 255)
    private String nombre;

    @NotNull
    @Size(min = 3, max = 255)
    private String apellido1;

    @NotNull
    @Size(min = 3, max = 255)
    private String apellido2;

    @NotNull
    private Long DNI;

    @NotNull
    private String feedback;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long CP;

    @CreationTimestamp
    @Column(name = "creado_en")
    private Timestamp fecha_creacion;

    @UpdateTimestamp
    @Column(name = "ultimo_login_en")
    private Timestamp ultima_conexion;

    @NotNull
    private String password;

    @NotNull
    private String telefono;

    @NotNull
    private boolean isActive;

    @NotNull
    private int saldo;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private java.util.List<GcontrataEntity> Gcontrata = new java.util.ArrayList<>();


    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_tipousuario")
    private TipousuarioEntity tipousuario;

    public UsuarioEntity() {}

    public UsuarioEntity(String username, String nombre, String apellido1, String apellido2, String email, boolean isActive, Long DNI, String feedback, Long CP, int saldo, String telefono, TipousuarioEntity tipousuario) {
        this.username = username;   
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.DNI = DNI;
        this.feedback = feedback;
        this.CP = CP;
        this.saldo = saldo;
        this.telefono = telefono;
        this.isActive = isActive;
        this.tipousuario = tipousuario;
        this.fecha_creacion = new Timestamp(System.currentTimeMillis());
        this.ultima_conexion = null;
    }

    public UsuarioEntity(Long id, String username, String nombre, String apellido1, String apellido2, String email, Long DNI, String feedback, Long CP,int saldo, String telefono, TipousuarioEntity tipousuario) {
        this.id = id;
        this.username = username;   
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.DNI = DNI;
        this.feedback = feedback;
        this.CP = CP;
        this.saldo = saldo;
        this.telefono = telefono;
        this.tipousuario = tipousuario;
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public TipousuarioEntity getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipousuarioEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getDNI() {
        return DNI;
    }

    public void setDNI(Long dNI) {
        DNI = dNI;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Long getCP() {
        return CP;
    }

    public void setCP(Long cP) {
        CP = cP;
    }

    public Timestamp getCreadoEn() {
        return fecha_creacion;
    }

    public void setCreadoEn(Timestamp creadoEn) {
        this.fecha_creacion = creadoEn;
    }

    public Timestamp getUltimoLoginEn() {
        return ultima_conexion;
    }

    public void setUltimoLoginEn(Timestamp ultimoLoginEn) {
        this.ultima_conexion = ultimoLoginEn;
    }

    public int getGcontrataCount() {
        return Gcontrata.size();
    }

//  public TipousuarioEntity getTipousuario() {
//     return tipousuario;
//  }

//  public void setTipousuario(TipousuarioEntity tipousuario) {
//     this.tipousuario = tipousuario;
//  }
    
    

}