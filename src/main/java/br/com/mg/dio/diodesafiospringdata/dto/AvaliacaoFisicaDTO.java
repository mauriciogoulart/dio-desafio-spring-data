package br.com.mg.dio.diodesafiospringdata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AvaliacaoFisicaDTO {
	
	private Long idAluno;
	private double peso;
	private double altura;

}
