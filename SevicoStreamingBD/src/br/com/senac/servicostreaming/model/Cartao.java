/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.servicostreaming.model;
public class Cartao {
    
    private int id;
    private String numero;
    private String nomeTitular;
    private String validade; // Formato: MM/AAAA
    private String codigoSeguranca;
    private String tipo;
    private Usuario usuario;

    public Cartao() {
    }

    public Cartao(int id, String numero, String nomeTitular, String validade, String codigoSeguranca, Usuario usuario, String tipo) {
        this.id = id;
        this.numero = numero;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.codigoSeguranca = codigoSeguranca;
        this.tipo = tipo;
        this.usuario = usuario;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }
    
     public Usuario getUsuario() {
        return usuario;  
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario; 
    }
}
