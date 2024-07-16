package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;


    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.activo = true;
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());

    }

    public void actualizarDatos(DatosActualizadMedico datosActualizadMedico) {
        if(datosActualizadMedico.nombre() != null){
            this.nombre = datosActualizadMedico.nombre();
        }
        if(datosActualizadMedico.documento() != null){
            this.documento = datosActualizadMedico.documento();
        }
        if(datosActualizadMedico.direccion() != null){
            this.direccion.actualizarDireccion(datosActualizadMedico.direccion());
        }

    }

    public void desactivarMedico(Medico medico) {
        this.activo = false;
    }
}
