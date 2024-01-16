package b.daw.cifpcm.animales_persistencia.models;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotBlank(message = "Campo Obligatorio")
    @Size(min = 3, max = 15, message = "ERROR: Debe tener entre 3 y 15 caracteres...")
    private String Nombre;
    @NotNull(message = "Campo Obligatorio")
    @Min(0)
    @Max(600)
    @Digits(integer = 600, fraction = 0, message = "Error: El rango debe ser entre 0 y 600 ...")
    private Integer VidaMedia;
    @NotNull
    private Boolean Extinto;
}
