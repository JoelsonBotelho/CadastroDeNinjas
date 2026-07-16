package dev.jav10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota";
    }

    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listarID")
    public String listarNinjaPorID(){
        return "Listar Ninja por ID";
    }
    @PutMapping("/alterarID")
    public String alterarNinjaPorID(){
        return "Alterar por ID";
    }
    @DeleteMapping("/deletarID")
    public String deletarPorID(){
        return "Deletar por ID";
    }

}
