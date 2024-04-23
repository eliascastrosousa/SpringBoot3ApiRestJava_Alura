package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoIdConsultaCancelamento implements ValidacaoCancelamentoConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosCancelamentoConsulta dados){



        if (dados.idConsulta() == null) {
            throw new ValidacaoException("Id da consulta não pode ser nulo!");
        }

    }

}
