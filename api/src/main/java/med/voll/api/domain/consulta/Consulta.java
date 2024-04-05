package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "consultas")
@Entity(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
    private Paciente paciente;
    private Medico medico;
    private Date dataEHora;

    public Consulta(DadosCadastroConsulta dados){
        this.paciente = new Paciente(dados.paciente());
        this.medico = new Medico(dados.medico());
        this.dataEHora = dados.data();
    }
     */
}
