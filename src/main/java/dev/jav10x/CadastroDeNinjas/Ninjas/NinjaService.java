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
    
    public NinjaModel listarNinjaPorID(Long id) {
        return ninjaRepository.findById(id).orElse(null);
    }
    
    public NinjaModel criarNinja(NinjaModel ninja) {
        return ninjaRepository.save(ninja);
    }

    public void deletarNinjaPorID(Long id) {
        ninjaRepository.deleteById(id);
    }

    public NinjaModel alterarNinjaPorID(Long id, NinjaModel ninja) {
        if(ninjaRepository.existsById(id)){
            ninja.setId(id);
            return ninjaRepository.save(ninja);
        }
        return null;
    }
}
