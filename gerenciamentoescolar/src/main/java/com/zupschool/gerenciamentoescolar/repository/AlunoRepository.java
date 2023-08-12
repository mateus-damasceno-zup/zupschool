package com.zupschool.gerenciamentoescolar.repository;

import com.zupschool.gerenciamentoescolar.Model.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno,Long> {
}
