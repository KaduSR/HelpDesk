package com.carlos.HelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.carlos.HelpDesk.domain.Pessoa;
import com.carlos.HelpDesk.domain.Tecnico;
import com.carlos.HelpDesk.domain.dtos.TecnicoDto;
import com.carlos.HelpDesk.repositories.PessoaRepository;
import com.carlos.HelpDesk.repositories.TecnicoRepository;
import com.carlos.HelpDesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

   
    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    
	

  
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id ));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDto objDTO) {
        objDTO.setId(null);
        validaPorCpfEmail(objDTO); //Metodo para validação de CPF e E-mail no banco de dados para criar uma exeção 
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }
    
    private void validaPorCpfEmail(TecnicoDto objDTO) {
       Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf()) ;
       if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
        throw new DataIntegrityViolationException("CPF já Cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() !=objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no Sistema!"); 
     }
    }
}
