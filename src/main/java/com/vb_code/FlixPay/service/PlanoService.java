package com.vb_code.FlixPay.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.vb_code.FlixPay.database.entity.PlanoModel;
import com.vb_code.FlixPay.database.repository.IPlanoRepository;
import com.vb_code.FlixPay.dto.response.PlanoResponseDTO;
import com.vb_code.FlixPay.dto.resquest.PlanoRequestDTO;
import com.vb_code.FlixPay.exception.ConflitoException;
import com.vb_code.FlixPay.exception.IdNaoEncontradoException;
import com.vb_code.FlixPay.mapper.IPlanoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final IPlanoRepository repository;
    private final IPlanoMapper mapper;

    //POST
    public PlanoResponseDTO cadastrar(PlanoRequestDTO dto) {
        if (repository.existsByNomeIgnoreCaseAndAtivoTrue(dto.nome())) {
            throw new ConflitoException("Já existe uma categoria ativa com esse nome.");
        }

        var planoSalvo = repository.save(mapper.toEntity(dto));

        return mapper.toResponse(planoSalvo);
    }

    //GET
    public List<PlanoResponseDTO> listar() {
        List<PlanoModel> planolist = repository.findAllByAtivoTrue();

        return planolist.stream()
                        .map(mapper::toResponse)
                        .toList();
    }

    //GET
    public PlanoResponseDTO buscarPorId(UUID id) {
        var plano = repository.findById(id)
                              .orElseThrow(() -> new IdNaoEncontradoException("Id não encontrado"));

        return mapper.toResponse(plano);
    }

    //DELETE
    public void desativar(UUID id) {
        var plano = repository.findById(id)
                              .orElseThrow(() -> new IdNaoEncontradoException("Id não encontrado"));

        if (!plano.getAtivo()) {
            throw new ConflitoException("O plano se encontra inativo");
        }

        plano.setAtivo(false);

        repository.save(plano);
    }

}
