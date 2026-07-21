package dev.jav10x.CadastroDeNinjas.Missoes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/criar")
    public ResponseEntity <String> criarMissao(@RequestBody MissoesDTO missoesDTO){
        MissoesDTO missao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Missão criada com sucesso: \nNome: " + missao.getNome() + "\nID: " + missao.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissao(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity <?> listarMissaoPorID(@PathVariable Long id){
        MissoesDTO missao = missoesService.listarMissaoPorId(id);
        if(missao != null){
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Missão com Id: "+ id +" não encontrada.");
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity <?> alterarMissaoPorID(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO){
        MissoesDTO missao = missoesService.alterarMissaoPorId(id, missoesDTO);
        if(missao != null){
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Missão com Id: "+ id +" não encontrada.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorID(@PathVariable Long id){
        if(missoesService.listarMissaoPorId(id) != null){
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.status(HttpStatus.OK)
            .body("Missão com Id: " + id + " deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Missão com Id: "+ id +" não encontrada.");
        }
    }
}
