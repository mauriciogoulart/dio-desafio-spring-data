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

import br.com.mg.dio.diodesafiospringdata.dto.MatriculaDTO;
import br.com.mg.dio.diodesafiospringdata.exception.RegistroNotFoundException;
import br.com.mg.dio.diodesafiospringdata.service.MatriculaService;

@RestController
@RequestMapping("api/v1/matriculas")
public class MatriculaController {
	
	@Autowired
	private MatriculaService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MatriculaDTO> create(@RequestBody MatriculaDTO matriculaDTO) throws RegistroNotFoundException {
		MatriculaDTO matriculaPersistente = service.create(matriculaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(matriculaPersistente);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Long id) throws RegistroNotFoundException {
		service.delete(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MatriculaDTO>> findAll() {
		List<MatriculaDTO> matriculas = service.findAll();
		return ResponseEntity.ok(matriculas);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MatriculaDTO> findById(@PathVariable("id") Long id) throws RegistroNotFoundException {
		MatriculaDTO matricula = service.findById(id);
		return ResponseEntity.ok(matricula);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MatriculaDTO> update(@PathVariable("id") Long id, @RequestBody MatriculaDTO matriculaDTO)
			throws RegistroNotFoundException {
		MatriculaDTO matricula = service.update(id, matriculaDTO);
		return ResponseEntity.ok(matricula);
	}

}
