package br.com.mg.dio.diodesafiospringdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mg.dio.diodesafiospringdata.dto.MatriculaDTO;
import br.com.mg.dio.diodesafiospringdata.entity.Aluno;
import br.com.mg.dio.diodesafiospringdata.entity.Matricula;
import br.com.mg.dio.diodesafiospringdata.exception.RegistroNotFoundException;
import br.com.mg.dio.diodesafiospringdata.repository.MatriculaRepository;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository repository;

	@Autowired
	private AlunoService alunoService;

	public MatriculaDTO create(MatriculaDTO matriculaDTO) throws RegistroNotFoundException {
		Matricula matricula = new Matricula();
		popularAluno(matriculaDTO, matricula);
		repository.save(matricula);
		return matriculaDTO;
	}

	private void popularAluno(MatriculaDTO matriculaDTO, Matricula matricula) throws RegistroNotFoundException {
		Aluno aluno = alunoService.findAlunoById(matriculaDTO.getIdAluno());
		matricula.setAluno(aluno);
	}

	public void delete(Long id) throws RegistroNotFoundException {
		Matricula matricula = findMatriculaById(id);
		repository.delete(matricula);
	}

	public List<MatriculaDTO> findAll() {
		List<Matricula> matriculas = repository.findAll();
		List<MatriculaDTO> matriculasDTO = matriculas.stream().map(matricula -> converterMatriculaParaDTO(matricula)).collect(Collectors.toList());
		return matriculasDTO;
	}

	private MatriculaDTO converterMatriculaParaDTO(Matricula matricula) {
		MatriculaDTO dto = new MatriculaDTO();
		dto.setIdAluno(matricula.getAluno().getId());
		return dto;
	}

	public MatriculaDTO findById(Long id) throws RegistroNotFoundException {
		Matricula matricula = findMatriculaById(id);
		MatriculaDTO dto = converterMatriculaParaDTO(matricula);
		return dto;
	}

	private Matricula findMatriculaById(Long id) throws RegistroNotFoundException {
		return repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));
	}

	public MatriculaDTO update(Long id, MatriculaDTO matriculaDTO) throws RegistroNotFoundException {
		Matricula matriculaPersistente = findMatriculaById(id);
		Matricula matriculaSalvar = new Matricula();
		popularAluno(matriculaDTO, matriculaSalvar);
		matriculaSalvar.setId(id);
		BeanUtils.copyProperties(matriculaSalvar, matriculaPersistente);
		popularAluno(matriculaDTO, matriculaPersistente);
		repository.save(matriculaPersistente);
		return matriculaDTO;
	}

}
