/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.servicostreaming.model;

import java.time.LocalDateTime;

public class Sugestao {
    private int id;
    private String nome;
    private LocalDateTime dataHora;
    private String descricao;
    private Usuario usuario;
    
    public Sugestao(String nome, String descricao) {
        this.id = id; 
        this.nome = nome;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    public Sugestao() {
    }

  
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sugestao(int id, String nome, String dataHora, String descricao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.dataHora = LocalDateTime.now();
        this.descricao = descricao;
        this.usuario = usuario;
    } 
}
