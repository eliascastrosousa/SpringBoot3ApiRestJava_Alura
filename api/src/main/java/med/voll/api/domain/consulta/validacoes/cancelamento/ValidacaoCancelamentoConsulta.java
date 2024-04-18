package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidacaoCancelamentoConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
