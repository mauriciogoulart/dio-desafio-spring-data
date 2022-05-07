package br.com.mg.dio.diodesafiospringdata.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Aluno {

	@Id
	private Long id;
	private String nome;
	private String cpf;
	private String bairro;
	private LocalDate dataNascimento;
	@OneToMany
	private List<AvaliacaoFisica> avaliacoes;
	
	
}
