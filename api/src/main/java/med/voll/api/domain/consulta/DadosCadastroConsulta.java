package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.paciente.DadosCadastroPaciente;

import java.util.Date;

public record DadosCadastroConsulta(
        @NotNull
        @Valid
        DadosCadastroPaciente paciente,
        @NotNull
        @Valid
        DadosCadastroMedico medico,
        @NotNull
        Date data
) {
}
