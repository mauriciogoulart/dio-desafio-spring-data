package br.com.mg.dio.diodesafiospringdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mg.dio.diodesafiospringdata.dto.AlunoDTO;
import br.com.mg.dio.diodesafiospringdata.exception.AlunoNotFoundException;
import br.com.mg.dio.diodesafiospringdata.service.AlunoService;

@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

	@Autowired
	private AlunoService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO) {
		AlunoDTO alunoPersistente = service.create(alunoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoPersistente);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Long id) throws AlunoNotFoundException {
		service.delete(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlunoDTO>> findAll() {
		List<AlunoDTO> alunos = service.findAll();
		return ResponseEntity.ok(alunos);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlunoDTO> findById(@PathVariable("id") Long id) throws AlunoNotFoundException {
		AlunoDTO aluno = service.findById(id);
		return ResponseEntity.ok(aluno);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlunoDTO> update(@PathVariable("id") Long id, @RequestBody AlunoDTO alunoDTO)
			throws AlunoNotFoundException {
		AlunoDTO aluno = service.update(id, alunoDTO);
		return ResponseEntity.ok(aluno);
	}

}
