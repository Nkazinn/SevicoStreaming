/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import Conexao.Conexao;
import br.com.senac.servicostreaming.model.Sugestao;
import br.com.senac.servicostreaming.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SugestaoDao {
    
    private Usuario u;
    PreparedStatement st;
    ResultSet rs;
    private Conexao conexao;
    private Connection con;
    
   public SugestaoDao() {
        this.conexao = new Conexao();
        this.con = (Connection) this.conexao.conectar();
    }

   public int addSugestao(int id, String nome, String descricao) {
    int status = 0;
    

    String sqlVerificarUsuario = "SELECT id FROM usuarios WHERE id = ?";
    String sqlVerificarAdministrador = "SELECT id FROM administradores WHERE id = ?";
    String sqlInserirSugestao = "INSERT INTO sugestoes (usuario_id, nome, descricao, data_hora) VALUES (?, ?, ?, NOW())";
    
    try {
        
        st = con.prepareStatement(sqlVerificarUsuario);
        st.setInt(1, id);
        rs = st.executeQuery();

        st = con.prepareStatement(sqlVerificarAdministrador);
        st.setInt(1, id);
        rs = st.executeQuery();
        
        st = con.prepareStatement(sqlInserirSugestao);
        st.setInt(1, id);
        st.setString(2, nome);
        st.setString(3, descricao);

        status = st.executeUpdate();
        System.out.println("Sugestão inserida com sucesso, status: " + status);
        
        st.close();
        
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar: " + ex.getMessage());
        ex.printStackTrace(); 
        return ex.getErrorCode(); 
    }
    
    return status;
}
    
    public List<Sugestao> getSugestoes() {
        List<Sugestao> lista = new ArrayList<>();
        String sql = "SELECT * FROM sugestoes";

        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Sugestao s = new Sugestao();
                s.setId(rs.getInt("id"));
                s.setNome(rs.getString("nome"));
                s.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime()); // Ajuste se necessário
                s.setDescricao(rs.getString("descricao"));

                lista.add(s);
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar sugestões: " + ex.getMessage());
        }

        return lista;
    }
}
