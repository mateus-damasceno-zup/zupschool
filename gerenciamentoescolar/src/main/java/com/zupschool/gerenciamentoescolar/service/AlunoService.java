package com.zupschool.gerenciamentoescolar.service;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.DTO.MatriculaDTO;
import com.zupschool.gerenciamentoescolar.Model.Aluno;
import com.zupschool.gerenciamentoescolar.Model.Matricula;
import com.zupschool.gerenciamentoescolar.mapping.MappingService;
import com.zupschool.gerenciamentoescolar.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;
    @Autowired
    private MappingService mappingService;
    public AlunoService(AlunoRepository alunoRepository, MappingService mappingService) {
        this.alunoRepository = alunoRepository;
        this.mappingService = mappingService;
    }

    public List<AlunoDTO> listaAlunos (){
        Iterable<Aluno> alunos = alunoRepository.findAll();
        List<AlunoDTO> alunoDTOS = new ArrayList<>();
        for (Aluno aluno: alunos){
            AlunoDTO alunoDTOs = mappingService.mapAlunoToDTO(aluno);
            alunoDTOS.add(alunoDTOs);

        }

        return alunoDTOS;
    }
    public Optional<AlunoDTO> getAlunoPorId(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isEmpty()) {

            return Optional.empty();
        }
        return aluno.map(a->mappingService.mapAlunoToDTO(a));
    }
    public AlunoDTO cadastraAluno(AlunoDTO alunoDTO) {
        Aluno aluno = mappingService.mapDTOToAluno(alunoDTO);
        Aluno alunoSaved = alunoRepository.save(aluno);
        return mappingService.mapAlunoToDTO(alunoSaved);
    }

    public AlunoDTO atualizaAluno(AlunoDTO alunoDTO){
       AlunoDTO alunoAtual = getAlunoPorId(alunoDTO.getId()).orElse(null);
        if (alunoAtual!=null){
            alunoAtual.setId(alunoAtual.getId());
            alunoAtual.setNome(alunoDTO.getNome());
            alunoAtual.setEmail(alunoDTO.getEmail());
            alunoAtual.setIdade(alunoDTO.getIdade());
        Aluno aluno = mappingService.mapDTOToAluno(alunoAtual);
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return mappingService.mapAlunoToDTO(alunoSalvo);
        }
       return null;
    }
    public void deletaAluno(Long id) {
        alunoRepository.deleteById(id);
    }


}
