/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.servicostreaming.model;
public class UsuarioModerador {
    
    private int id;
    private Moderador moderador;
    private Usuario usuario;
    
      public UsuarioModerador(int id, Moderador moderador, Usuario usuario) {
        this.id = id;
        this.moderador = moderador;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Moderador getModerador() {
        return moderador;
    }

    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
}
