/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.servicostreaming.model;
public class Avaliacaom{
    private int id;
    private Usuario usuario;
    private Conteudo conteudo;
    private int nota;

    public Avaliacaom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    public Avaliacaom(int id, Usuario usuario, Conteudo conteudo, int nota) {
        this.id = id;
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.nota = nota;
    }  
}
