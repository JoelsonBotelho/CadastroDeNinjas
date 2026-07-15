package dev.jav10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missoes")
public class MissoesController {
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missão criada com sucesso";
    }

    @GetMapping("/listar")
    public String listarMissao(){
        return "Listando Missões";
    }

    @GetMapping("/listarID")
    public String listarMissaoPorID(){
        return "Listar Missão por ID"; 
    }

    @PutMapping("/alterarID")
    public String alterarMissaoPorID(){
        return "Alterar Missão por ID";
    }

    @DeleteMapping("/deletarID")
    public String deletarMissaoPorID(){
        return "Deletar Missão por ID";
    }
}
