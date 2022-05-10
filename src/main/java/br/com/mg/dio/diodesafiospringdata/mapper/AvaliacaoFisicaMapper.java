package br.com.mg.dio.diodesafiospringdata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.mg.dio.diodesafiospringdata.dto.AvaliacaoFisicaDTO;
import br.com.mg.dio.diodesafiospringdata.entity.AvaliacaoFisica;

@Mapper
public interface AvaliacaoFisicaMapper {

	AvaliacaoFisicaMapper INSTANCE = Mappers.getMapper(AvaliacaoFisicaMapper.class);

	AvaliacaoFisica toModel(AvaliacaoFisicaDTO avaliacaoFisicaDTO);

	//REMOVER mauricio verificar copia valores mapper
	 @Mappings({
	      @Mapping(target="idAluno", source="avaliacaoFisica.aluno.id"),
	    })
	AvaliacaoFisicaDTO toDTO(AvaliacaoFisica avaliacaoFisica);

}
