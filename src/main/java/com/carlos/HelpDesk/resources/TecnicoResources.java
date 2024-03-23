package com.carlos.HelpDesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.HelpDesk.domain.Tecnico;
import com.carlos.HelpDesk.domain.dtos.TecnicoDto;
import com.carlos.HelpDesk.services.TecnicoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResources {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }

    @GetMapping 
    public ResponseEntity<List<TecnicoDto>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDto> listDTO = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
