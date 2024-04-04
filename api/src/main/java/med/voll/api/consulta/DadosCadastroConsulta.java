package med.voll.api.consulta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.paciente.DadosCadastroPaciente;

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
