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

import br.com.mg.dio.diodesafiospringdata.dto.AvaliacaoFisicaDTO;
import br.com.mg.dio.diodesafiospringdata.exception.RegistroNotFoundException;
import br.com.mg.dio.diodesafiospringdata.service.AvaliacaoFisicaService;

@RestController
@RequestMapping("api/v1/avaliacoes-fisicas")
public class AvaliacaoFisicaController {

	@Autowired
	private AvaliacaoFisicaService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AvaliacaoFisicaDTO> create(@RequestBody AvaliacaoFisicaDTO avaliacaoFisicaDTO) throws RegistroNotFoundException {
		AvaliacaoFisicaDTO avaliacaoFisicaPersistente = service.create(avaliacaoFisicaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoFisicaPersistente);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Long id) throws RegistroNotFoundException {
		service.delete(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AvaliacaoFisicaDTO>> findAll() {
		List<AvaliacaoFisicaDTO> avaliacaoFisicas = service.findAll();
		return ResponseEntity.ok(avaliacaoFisicas);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AvaliacaoFisicaDTO> findById(@PathVariable("id") Long id) throws RegistroNotFoundException {
		AvaliacaoFisicaDTO avaliacaoFisica = service.findById(id);
		return ResponseEntity.ok(avaliacaoFisica);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AvaliacaoFisicaDTO> update(@PathVariable("id") Long id, @RequestBody AvaliacaoFisicaDTO avaliacaoFisicaDTO)
			throws RegistroNotFoundException {
		AvaliacaoFisicaDTO avaliacaoFisica = service.update(id, avaliacaoFisicaDTO);
		return ResponseEntity.ok(avaliacaoFisica);
	}
	
}
