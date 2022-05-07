package br.com.mg.dio.diodesafiospringdata.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AvaliacaoFisica {
	
	@Id
	private Long id;
	@ManyToOne
	private Aluno aluno;
	private LocalDateTime dataAvaliacao = LocalDateTime.now();
	private double peso;
	private double altura;
	

}
