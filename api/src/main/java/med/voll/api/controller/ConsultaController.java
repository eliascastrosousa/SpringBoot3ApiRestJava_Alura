package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.consulta.Consulta;
import med.voll.api.consulta.ConsultaRepository;
import med.voll.api.consulta.DadosCadastroConsulta;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamento-de-consultas")
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(
            @RequestBody @Valid DadosCadastroConsulta dados){
        repository.save(new Consulta(dados));
    }
}
