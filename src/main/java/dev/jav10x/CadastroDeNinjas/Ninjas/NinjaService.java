package dev.jav10x.CadastroDeNinjas.Ninjas;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NinjaService {
    
    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    }
    
}
