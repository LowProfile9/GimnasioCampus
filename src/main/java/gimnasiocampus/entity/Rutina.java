package gimnasiocampus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rutina")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    @NotBlank
    private String nombre;

    @Column(name = "nivel", nullable = false)
    @NotBlank
    private String nivel;

    @Version
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long version;

    @ManyToMany(mappedBy = "rutinas")
    @JsonIgnore
    private List<Cliente> clientes = new ArrayList<>();

    public Rutina() {
    }

    public Rutina(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    
}