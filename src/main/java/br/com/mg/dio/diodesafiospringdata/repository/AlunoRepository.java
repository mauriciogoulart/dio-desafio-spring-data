package br.com.mg.dio.diodesafiospringdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mg.dio.diodesafiospringdata.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
