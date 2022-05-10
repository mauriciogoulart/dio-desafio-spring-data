package br.com.mg.dio.diodesafiospringdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlunoNotFoundException extends Exception {

	private static final long serialVersionUID = -220950167702121713L;

	public AlunoNotFoundException(Long id) {
		super("Aluno com id " + id + " nao encontrado");
	}

}
