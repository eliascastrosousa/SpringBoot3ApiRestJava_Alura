package med.voll.api.controller;

import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamento-de-consultas")
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(){
    }
}
