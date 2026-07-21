package dev.jav10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;
    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }
    
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota";
    }

    @PostMapping("/criar")
    public ResponseEntity <String> criarNinja(@RequestBody NinjaDTO ninjaDTO){
        
        NinjaDTO ninja = ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Ninja criado com sucesso: \nNome: " + ninja.getNome() + "\nID: " + ninja.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity <List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity <?> listarNinjaPorID(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjaPorID(id);
        if(ninja != null){
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Ninja com Id: "+ id +" não encontrado.");
        }
    }
    
    @PutMapping("/alterar/{id}")
    public ResponseEntity <?> alterarNinjaPorID(@PathVariable Long id, @RequestBody NinjaDTO ninjaDTO){
        NinjaDTO updatedNinja = ninjaService.alterarNinjaPorID(id, ninjaDTO);
        if(updatedNinja != null){
            return ResponseEntity.ok(updatedNinja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Ninja com Id: "+ id +" não encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity <String> deletarPorID(@PathVariable Long id){
        if(ninjaService.listarNinjaPorID(id) != null){
            ninjaService.deletarNinjaPorID(id);
            return ResponseEntity.status(HttpStatus.OK)
            .body("Ninja com ID: "+ id +" deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Ninja não encontrado");
        }
    }

}
