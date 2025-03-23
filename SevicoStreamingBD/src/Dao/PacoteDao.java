/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import br.com.senac.servicostreaming.model.Cartao;
import br.com.senac.servicostreaming.model.PacoteM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacoteDao {
    PreparedStatement st;
    ResultSet rs;
    private Conexao conexao;
    private Connection con;
    
    public PacoteDao() {
        this.conexao = new Conexao();
        this.con = (Connection) this.conexao.conectar();
    }
    
   public int addPacote(PacoteM pacote) {
        int status;
        try {
            String sql = "INSERT INTO pacotes (nome, valor, descricao) VALUES ( ?, ?, ?)";
            st = con.prepareStatement(sql);

            st.setString(1, pacote.getNome());
            st.setDouble(2, pacote.getValor());
            st.setString(3, pacote.getDescricao());

            status = st.executeUpdate();
            
            if (status == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir pacote: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
   
   public PacoteM buscarPacotePorNome(String nomePacote) {
    PacoteM pacote = null;
    try {
        String sql = "SELECT * FROM pacotes WHERE nome = ?";
        
        st = con.prepareStatement(sql);
        st.setString(1, nomePacote);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            pacote = new PacoteM(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("valor"),
                rs.getString("descricao")
            );
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao buscar pacote: " + ex.getMessage());
    }
    return pacote;
  }
   
   public PacoteM buscarPorId(int id) {
        PacoteM pacote = null;
        
        String sql = "SELECT * FROM pacotes WHERE id = ?";
        
        try {
                
            st = con.prepareStatement(sql);
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                pacote = new PacoteM(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("valor"),
                rs.getString("descricao")
              );            
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pacote;  // Retorna o usuário encontrado ou null caso não exista
    }
    
 }

