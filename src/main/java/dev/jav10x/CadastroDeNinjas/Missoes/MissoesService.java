package dev.jav10x.CadastroDeNinjas.Missoes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class MissoesService {

     private MissoesRepository missoesRepository;
     private MissoesMapper missoesMapper;

     public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = new MissoesMapper();
     }

     public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream().map(missoesMapper::map).collect(Collectors.toList());
     }

        public MissoesDTO listarMissaoPorId(Long id) {
            Optional<MissoesModel> missao = missoesRepository.findById(id);
            return missao.map(missoesMapper::map).orElse(null);
        }

     public MissoesDTO criarMissao(MissoesDTO missoesDTO) {
        MissoesModel missao = missoesMapper.map(missoesDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
     }

     public void deletarMissaoPorId(Long id){
        missoesRepository.deleteById(id);
     }

     public MissoesDTO alterarMissaoPorId(Long id, MissoesDTO missoesDTO) {
        MissoesModel missaoExistente = missoesRepository.findById(id).orElse(null);
        if (missaoExistente != null) {
            MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
    
}