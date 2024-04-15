package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    public Consulta agendar(DadosAgendamentoConsulta dados){
        //validações

        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe");
        }

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente,dados.data(), null);
        consultaRepository.save(consulta);
        System.out.println(consulta.toString());
        return consulta;
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if (!consultaRepository.existsById(dados.idConsulta())){ // se nao existir
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        // a validação do motivo ja é feito pelo bean validation

        var getConsulta = consultaRepository.getReferenceById(dados.idConsulta());
        // peguei a consulta e chamei o metodo cancelar da sua classe p ela ao qual ira
        // atualizar o objeto no banco adicionando o motivo do cancelamento
        getConsulta.cancelar(dados.motivoCancelamento());
    }


}
