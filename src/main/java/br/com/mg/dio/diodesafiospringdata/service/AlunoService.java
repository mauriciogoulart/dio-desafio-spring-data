package br.com.mg.dio.diodesafiospringdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mg.dio.diodesafiospringdata.dto.AlunoDTO;
import br.com.mg.dio.diodesafiospringdata.dto.AvaliacaoFisicaDTO;
import br.com.mg.dio.diodesafiospringdata.entity.Aluno;
import br.com.mg.dio.diodesafiospringdata.exception.RegistroNotFoundException;
import br.com.mg.dio.diodesafiospringdata.mapper.AlunoMapper;
import br.com.mg.dio.diodesafiospringdata.mapper.AvaliacaoFisicaMapper;
import br.com.mg.dio.diodesafiospringdata.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	private final AlunoMapper alunoMapper = AlunoMapper.INSTANCE;

	private final AvaliacaoFisicaMapper avaliacaoFisicaMapper = AvaliacaoFisicaMapper.INSTANCE;

	public AlunoDTO create(AlunoDTO alunoDTO) {
		Aluno aluno = alunoMapper.toModel(alunoDTO);
		repository.save(aluno);
		return alunoMapper.toDTO(aluno);
	}

	public void delete(Long id) throws RegistroNotFoundException {
		Aluno aluno = findAlunoById(id);
		repository.delete(aluno);
	}

	public List<AlunoDTO> findAll() {
		List<Aluno> alunos = repository.findAll();
		List<AlunoDTO> alunosDTO = alunos.stream().map(alunoMapper::toDTO).collect(Collectors.toList());
		return alunosDTO;
	}

	public AlunoDTO findById(Long id) throws RegistroNotFoundException {
		Aluno aluno = findAlunoById(id);
		return alunoMapper.toDTO(aluno);
	}

	public Aluno findAlunoById(Long id) throws RegistroNotFoundException {
		return repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));
	}

	public AlunoDTO update(Long id, AlunoDTO alunoDTO) throws RegistroNotFoundException {
		Aluno alunoPersistente = findAlunoById(id);
		Aluno alunoSalvar = alunoMapper.toModel(alunoDTO);
		alunoSalvar.setId(id);
		BeanUtils.copyProperties(alunoSalvar, alunoPersistente);
		Aluno alunoAtualizado = repository.save(alunoPersistente);
		return alunoMapper.toDTO(alunoAtualizado);
	}

	public List<AvaliacaoFisicaDTO> findAllAvaliacoes(Long id) throws RegistroNotFoundException {
		return findAlunoById(id).getAvaliacoes().stream().map(avaliacao -> avaliacaoFisicaMapper.toDTO(avaliacao))
				.collect(Collectors.toList());
	}

}
