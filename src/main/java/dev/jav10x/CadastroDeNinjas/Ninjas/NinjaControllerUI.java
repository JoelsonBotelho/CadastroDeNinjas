package dev.jav10x.CadastroDeNinjas.Ninjas;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.jav10x.CadastroDeNinjas.Missoes.MissoesRepository;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {
    
    private final NinjaService ninjaService;
    private final MissoesRepository missoesRepository;
    
    public NinjaControllerUI(NinjaService ninjaService, MissoesRepository missoesRepository) {
        this.ninjaService = ninjaService;
        this.missoesRepository = missoesRepository;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPorNinja(@PathVariable Long id) {
        ninjaService.deletarNinjaPorID(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model){
        model.addAttribute("ninja", new NinjaDTO());
        model.addAttribute("missoes", missoesRepository.findAll());
        return "adicionarNinja";
    }

    @PostMapping("salvar")
    public String salvar(@ModelAttribute NinjaDTO ninja){
        ninjaService.criarNinja(ninja);
        return "redirect:/ninjas/ui/listar";
    }
}
