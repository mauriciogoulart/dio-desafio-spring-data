package br.com.mg.dio.diodesafiospringdata.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlunoDTO {

	private String nome;
	private String cpf;
	private String bairro;
	private LocalDate dataNascimento;
	
	
}
