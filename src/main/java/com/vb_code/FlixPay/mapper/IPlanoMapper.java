package com.vb_code.FlixPay.mapper;

import org.mapstruct.Mapper;
import com.vb_code.FlixPay.database.entity.PlanoModel;
import com.vb_code.FlixPay.dto.response.PlanoResponseDTO;
import com.vb_code.FlixPay.dto.resquest.PlanoRequestDTO;

@Mapper(componentModel = "spring")
public interface IPlanoMapper {

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "criadoEm", ignore = true)
    @org.mapstruct.Mapping(target = "ativo", ignore = true)
    PlanoModel toEntity(PlanoRequestDTO dto);

    PlanoResponseDTO toResponse(PlanoModel model);


}
