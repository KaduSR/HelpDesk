package com.carlos.HelpDesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.carlos.HelpDesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

/**
 * Esta classe representa a entidade Cliente, que é uma subclasse da classe Pessoa.
 */
@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore // Ignora a serialização dos chamados para evitar referência cíclica.
    @OneToMany(mappedBy = "cliente") // Define o relacionamento de um cliente com vários chamados.
    private List<Chamado> chamados = new ArrayList<>(); // Lista de chamados associados ao cliente.

    /**
     * Construtor padrão.
     * Inicializa a lista de perfis com o perfil CLIENTE.
     */
    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    /**
     * Construtor que inicializa os atributos da classe.
     *
     * @param id O ID do cliente.
     * @param nome O nome do cliente.
     * @param cpf O CPF do cliente.
     * @param email O e-mail do cliente.
     * @param senha A senha do cliente.
     */
    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    /**
     * Obtém a lista de chamados associados ao cliente.
     *
     * @return A lista de chamados associados ao cliente.
     */
    public List<Chamado> getChamados() {
        return this.chamados;
    }

    /**
     * Define a lista de chamados associados ao cliente.
     *
     * @param chamados A lista de chamados associados ao cliente.
     */
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}
