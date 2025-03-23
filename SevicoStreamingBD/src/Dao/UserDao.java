/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import br.com.senac.servicostreaming.model.Conteudo;
import br.com.senac.servicostreaming.model.PacoteM;
import br.com.senac.servicostreaming.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class UserDao {
    
    private PacoteM pacote;
    PreparedStatement st;
    ResultSet rs;
    private Conexao conexao;
    private Connection con;
    
    public UserDao() {
        this.conexao = new Conexao();
        this.con = this.conexao.conectar();
    }
    
    public Usuario validar(Usuario usuario) {
    Usuario usuarioEncontrado = null;

    // Consulta para verificar na tabela 'usuarios'
    String sqlUsuarios = "SELECT id, nome, email, senha, 'Usuário' AS tipo FROM usuarios WHERE email = ? AND senha = ?";
    
    // Consulta para verificar na tabela 'administradores'
    String sqlAdministradores = "SELECT id, nome, email, senha, 'Administrador' AS tipo FROM administradores WHERE email = ? AND senha = ?";

    try {
        PreparedStatement st = con.prepareStatement(sqlUsuarios);
        st.setString(1, usuario.getEmail());
        st.setString(2, usuario.getSenha());
        ResultSet rs = st.executeQuery();

        if (rs.next()) { 
            usuarioEncontrado = new Usuario();
            usuarioEncontrado.setId(rs.getInt("id"));
            usuarioEncontrado.setNome(rs.getString("nome"));
            usuarioEncontrado.setEmail(rs.getString("email"));
            usuarioEncontrado.setSenha(rs.getString("senha"));
            usuarioEncontrado.setTipo(rs.getString("tipo"));
        }

        rs.close();
        st.close();

        
        if (usuarioEncontrado == null) {
            st = con.prepareStatement(sqlAdministradores);
            st.setString(1, usuario.getEmail());
            st.setString(2, usuario.getSenha());
            rs = st.executeQuery();

            if (rs.next()) {
                usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(rs.getInt("id"));
                usuarioEncontrado.setNome(rs.getString("nome"));
                usuarioEncontrado.setEmail(rs.getString("email"));
                usuarioEncontrado.setSenha(rs.getString("senha"));
                usuarioEncontrado.setTipo(rs.getString("tipo")); 
            }

            rs.close();
            st.close();
        }

    } catch (SQLException ex) {
        System.out.println("Erro ao validar usuário: " + ex.getMessage());
    }

    return usuarioEncontrado;
}
    
    public  int cadastrar(Usuario usuario) {
        int status;
        String sql = "Insert into usuarios (nome,email,senha,tipo,possui_pacote) Values (?,?,?,?,?)";

        try {
           
             st = con.prepareStatement(sql);

            // Atribuindo os valores na consulta
            String tipo = (usuario.getTipo() != null && !usuario.getTipo().isEmpty()) ? usuario.getTipo() : "usuário";

            
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            st.setString(4, tipo);
            st.setString(5, "Não");
            status = st.executeUpdate();
           
            return status;
        }  catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
     public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
     
     public Usuario buscarPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        
        try {
                
            st = con.prepareStatement(sql);
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                // Preencher outros atributos conforme a tabela
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuario;  // Retorna o usuário encontrado ou null caso não exista
    }
   
    public boolean atualizar(PacoteM p,Usuario usuario) {
    String sql = "UPDATE usuarios SET pacote_id = ?, possui_pacote = ? WHERE id = ?";
    
    try {
        st = con.prepareStatement(sql);

        st.setInt(1, p.getId());
        st.setString(2,"Sim"); 
        st.setInt(3, usuario.getId());

        int status = st.executeUpdate();
        return status > 0; 

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
 }
    
}
