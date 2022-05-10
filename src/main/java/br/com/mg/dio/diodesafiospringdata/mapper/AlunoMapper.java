package br.com.mg.dio.diodesafiospringdata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.mg.dio.diodesafiospringdata.dto.AlunoDTO;
import br.com.mg.dio.diodesafiospringdata.entity.Aluno;

@Mapper
public interface AlunoMapper {

	AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

	@Mapping(target = "dataNascimento", source="dataNascimento", dateFormat = "dd-MM-yyyy")
	Aluno toModel(AlunoDTO alunoDTO);

	AlunoDTO toDTO(Aluno aluno);

}
