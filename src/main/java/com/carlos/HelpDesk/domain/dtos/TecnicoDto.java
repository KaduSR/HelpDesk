package com.carlos.HelpDesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.carlos.HelpDesk.domain.Tecnico;
import com.carlos.HelpDesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

/**
 * Esta classe representa o DTO (Data Transfer Object) para a entidade Tecnico.
 */
public class TecnicoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id; // ID do técnico.
    
    @NotNull(message = "O campo Nome é obrigatório.")
    protected String nome; // Nome do técnico.
    
    @NotNull(message = "O campo CPF é obrigatório")
    protected String cpf; // CPF do técnico.
    
    @NotNull(message = "O campo E-mail é obrigatório")
    protected String email; // E-mail do técnico.
    
    @NotNull(message = "O campo Senha é obrigatório")
    protected String senha; // Senha do técnico.
    
    protected Set<Integer> perfis = new HashSet<>(); // Conjunto de perfis do técnico.
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now(); // Data de criação do técnico.

    /**
     * Construtor padrão.
     * Inicializa o conjunto de perfis com o perfil CLIENTE.
     */
    public TecnicoDto() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    /**
     * Construtor que inicializa os atributos do DTO com base em um objeto Tecnico.
     *
     * @param obj O objeto Tecnico a partir do qual os atributos do DTO serão inicializados.
     */
    public TecnicoDto(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }

    // Getters e setters para os atributos do DTO.

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
