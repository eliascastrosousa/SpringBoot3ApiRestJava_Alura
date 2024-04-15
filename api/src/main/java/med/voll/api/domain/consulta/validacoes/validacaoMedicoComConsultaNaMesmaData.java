package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class validacaoMedicoComConsultaNaMesmaData {
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNaMesmaData = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (!medicoPossuiOutraConsultaNaMesmaData) {
            throw new ValidacaoException("Médico já possui consulta nesta mesma data.");
        }

    }
}
