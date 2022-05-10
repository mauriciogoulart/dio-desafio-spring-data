package br.com.mg.dio.diodesafiospringdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mg.dio.diodesafiospringdata.dto.AvaliacaoFisicaDTO;
import br.com.mg.dio.diodesafiospringdata.entity.Aluno;
import br.com.mg.dio.diodesafiospringdata.entity.AvaliacaoFisica;
import br.com.mg.dio.diodesafiospringdata.exception.RegistroNotFoundException;
import br.com.mg.dio.diodesafiospringdata.mapper.AvaliacaoFisicaMapper;
import br.com.mg.dio.diodesafiospringdata.repository.AvaliacaoFisicaRepository;

@Service
public class AvaliacaoFisicaService {

	@Autowired
	private AvaliacaoFisicaRepository repository;
	
	@Autowired
	private AlunoService alunoService;

	private final AvaliacaoFisicaMapper avaliacaoFisicaMapper = AvaliacaoFisicaMapper.INSTANCE;

	public AvaliacaoFisicaDTO create(AvaliacaoFisicaDTO avaliacaoFisicaDTO) throws RegistroNotFoundException {
		AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaMapper.toModel(avaliacaoFisicaDTO);
		popularAluno(avaliacaoFisicaDTO, avaliacaoFisica);
		repository.save(avaliacaoFisica);
		return avaliacaoFisicaMapper.toDTO(avaliacaoFisica);
	}

	private void popularAluno(AvaliacaoFisicaDTO avaliacaoFisicaDTO, AvaliacaoFisica avaliacaoFisica)
			throws RegistroNotFoundException {
		Aluno aluno = alunoService.findAlunoById(avaliacaoFisicaDTO.getIdAluno());
		avaliacaoFisica.setAluno(aluno);
	}

	public void delete(Long id) throws RegistroNotFoundException {
		AvaliacaoFisica avaliacaoFisica = findAvaliacaoFisicaById(id);
		repository.delete(avaliacaoFisica);
	}

	public List<AvaliacaoFisicaDTO> findAll() {
		List<AvaliacaoFisica> avaliacaoFisicas = repository.findAll();
		List<AvaliacaoFisicaDTO> avaliacaoFisicasDTO = avaliacaoFisicas.stream().map(avaliacaoFisicaMapper::toDTO)
				.collect(Collectors.toList());
		return avaliacaoFisicasDTO;
	}

	public AvaliacaoFisicaDTO findById(Long id) throws RegistroNotFoundException {
		AvaliacaoFisica avaliacaoFisica = findAvaliacaoFisicaById(id);
		AvaliacaoFisicaDTO dto = avaliacaoFisicaMapper.toDTO(avaliacaoFisica);
		dto.setIdAluno(avaliacaoFisica.getAluno().getId());
		return dto;
	}

	private AvaliacaoFisica findAvaliacaoFisicaById(Long id) throws RegistroNotFoundException {
		return repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));
	}

	public AvaliacaoFisicaDTO update(Long id, AvaliacaoFisicaDTO avaliacaoFisicaDTO) throws RegistroNotFoundException {
		AvaliacaoFisica avaliacaoFisicaPersistente = findAvaliacaoFisicaById(id);
		AvaliacaoFisica avaliacaoFisicaSalvar = avaliacaoFisicaMapper.toModel(avaliacaoFisicaDTO);
		avaliacaoFisicaSalvar.setId(id);
		BeanUtils.copyProperties(avaliacaoFisicaSalvar, avaliacaoFisicaPersistente);
		popularAluno(avaliacaoFisicaDTO, avaliacaoFisicaPersistente);
		AvaliacaoFisica avaliacaoFisicaAtualizado = repository.save(avaliacaoFisicaPersistente);
		return avaliacaoFisicaMapper.toDTO(avaliacaoFisicaAtualizado);
	}

}
