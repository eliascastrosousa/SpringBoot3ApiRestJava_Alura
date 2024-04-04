package med.voll.api.consulta;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.Paciente;

import java.util.Date;
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
    @Embedded
    private Paciente paciente;
    @Embedded
    private Medico medico;
    private Date dataEHora;

    public Consulta(DadosCadastroConsulta dados){
        this.paciente = new Paciente(dados.paciente());
        this.medico = new Medico(dados.medico());
        this.dataEHora = dados.data();
    }
}
