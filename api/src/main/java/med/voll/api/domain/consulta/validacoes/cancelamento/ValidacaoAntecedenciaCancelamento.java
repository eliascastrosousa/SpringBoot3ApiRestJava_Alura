package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoAntecedenciaCancelamento implements ValidacaoCancelamentoConsulta {
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var dataConsulta = consulta.getData();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataConsulta).toHours();

        if (diferenca < 24){
            throw new ValidacaoException("Cancelamento da consulta deve ter antecedencia minima de 24 horas.");
        }
    }
}
