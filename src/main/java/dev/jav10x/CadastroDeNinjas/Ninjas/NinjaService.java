package dev.jav10x.CadastroDeNinjas.Ninjas;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class NinjaService {
    
    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = new NinjaMapper();
    }

    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream().map(ninjaMapper::map).collect(Collectors.toList());
    }
    
    public NinjaDTO listarNinjaPorID(Long id) {
       Optional<NinjaModel> ninja = ninjaRepository.findById(id);
       return ninja.map(ninjaMapper::map).orElse(null);
    }
    
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    public void deletarNinjaPorID(Long id) {
        ninjaRepository.deleteById(id);
    }

    public NinjaDTO alterarNinjaPorID(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()) {
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }
}