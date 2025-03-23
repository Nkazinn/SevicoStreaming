/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import br.com.senac.servicostreaming.model.Avaliacaom;
import br.com.senac.servicostreaming.model.Conteudo;
import br.com.senac.servicostreaming.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaliarDao {
    
    PreparedStatement st;
    ResultSet rs;
    private Conexao conexao;
    private Connection con;
    
    public AvaliarDao() {
        this.conexao = new Conexao();
        this.con = (Connection) this.conexao.conectar();
    }
    
     public Conteudo getConteudoId (int id){
         String sql = "SELECT * FROM conteudos WHERE id = ?";
         try {

             st = this.con.prepareStatement(sql);
             st.setInt(1, id);
             rs = st.executeQuery();

             Conteudo conteudo = new Conteudo();

             rs.next();
             conteudo.setId(rs.getInt("id"));
             conteudo.setTitulo(rs.getString("titulo"));
          
             return conteudo;
          
          //tratando o erro, caso ele ocorra
      } catch (Exception e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
  }
     
    public int atualizar(Avaliacaom avaliacao) {
        
    int status; 
    try {
        
        String sql = ("INSERT INTO avaliacoes (nota, usuario_id, conteudo_id) VALUES (?, ?, ?)");
        
        st = con.prepareStatement(sql);

        st.setInt(1, avaliacao.getNota()); 
        st.setInt(2, avaliacao.getUsuario().getId()); 
        st.setInt(3, avaliacao.getConteudo().getId());
        
        status = st.executeUpdate();
        
        if (status == 1) {
            return 1;  
        } else {
            return 0;
        }
        
    } catch (SQLException ex) {
        System.out.println("Erro ao atualizar: " + ex.getErrorCode());
        return ex.getErrorCode();
    }
 }
    
}
