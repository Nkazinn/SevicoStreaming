/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.servicostreaming.model;
public class Conteudo{
    private int id;
    private String tipoConteudo;
    private String titulo;
    private int quantiEpisodios;
    private String dataLancamento;
    private String autor;
    private String descricao;

    
     public Conteudo(int id, String tipoConteudo, String titulo, int quantiEpisodiosEtc, String dataLancamento, String autor, String descricao) {
        this.id = id;
        this.tipoConteudo = tipoConteudo;
        this.titulo = titulo;
        this.quantiEpisodios = quantiEpisodiosEtc;
        this.dataLancamento = dataLancamento;
        this.autor = autor;
        this.descricao = descricao;
    }
    
    public Conteudo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQuantiEpisodios() {
        return quantiEpisodios;
    }

    public void setQuantiEpisodios(int quantiEpisodiosEtc) {
        this.quantiEpisodios = quantiEpisodiosEtc;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}