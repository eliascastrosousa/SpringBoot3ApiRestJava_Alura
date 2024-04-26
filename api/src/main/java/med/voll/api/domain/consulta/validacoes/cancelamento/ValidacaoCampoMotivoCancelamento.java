package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoCampoMotivoCancelamento implements ValidacaoCancelamentoConsulta {
    public void validar(DadosCancelamentoConsulta dados){
        if (dados.motivoCancelamento() == null){
            throw new ValidacaoException("O campo motivo do cancelamento não deve ser nulo.");
        }
    }
}
