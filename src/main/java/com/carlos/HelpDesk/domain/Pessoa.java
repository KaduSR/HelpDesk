package com.carlos.HelpDesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.carlos.HelpDesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Esta classe abstrata representa uma pessoa, contendo atributos comuns a todas as entidades de pessoa.
 */
@Entity
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id; // ID da pessoa.

    protected String nome; // Nome da pessoa.

    @Column(unique = true)
    protected String cpf; // CPF da pessoa, único no sistema.

    @Column(unique = true)
    protected String email; // E-mail da pessoa, único no sistema.

    protected String senha; // Senha da pessoa.

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS") // Tabela para armazenar os perfis da pessoa.
    protected Set<Integer> perfis = new HashSet<>(); // Conjunto de perfis da pessoa.

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now(); // Data de criação da pessoa.

    /**
     * Construtor padrão.
     * Inicializa o conjunto de perfis com o perfil CLIENTE.
     */
    public Pessoa() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    /**
     * Construtor que inicializa os atributos da pessoa.
     *
     * @param id O ID da pessoa.
     * @param nome O nome da pessoa.
     * @param cpf O CPF da pessoa.
     * @param email O e-mail da pessoa.
     * @param senha A senha da pessoa.
     */
    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE); // Adicionando o perfil padrão de todos os usuários.
    }

    // Getters e setters para os atributos da pessoa.

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
        return this.perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pessoa)) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
