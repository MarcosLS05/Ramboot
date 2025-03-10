package Senet.Ramboot.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
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
    @Email
    private String email;



    @NotNull
    private String password;

    @NotNull
    private String telefono;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_tipousuario")
    private TipousuarioEntity tipousuario;

    public UsuarioEntity() {}

    public UsuarioEntity(String nombre, String username, String apellido1, String apellido2, String email, String password, String telefono) {
        this.username = username;   
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }

    public UsuarioEntity(Long id, String username, String nombre, String apellido1, String apellido2, String email, String password, String telefono) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.password = password;    
        this.telefono = telefono;
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

//  public TipousuarioEntity getTipousuario() {
//     return tipousuario;
//  }

//  public void setTipousuario(TipousuarioEntity tipousuario) {
//     this.tipousuario = tipousuario;
//  }
    
    

}