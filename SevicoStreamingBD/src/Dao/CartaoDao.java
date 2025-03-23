/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import br.com.senac.servicostreaming.model.Cartao;
import br.com.senac.servicostreaming.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartaoDao {
    
    PreparedStatement st;
    ResultSet rs;
    private Conexao conexao;
    private Connection con;
    
    public CartaoDao() {
        this.conexao = new Conexao();
        this.con = (Connection) this.conexao.conectar();
    }
    
    public String formatarData(String data) throws ParseException {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("MM/yyyy");
        SimpleDateFormat formatoSaida = new SimpleDateFormat("MM/yyyy");
        Date dataFormatada = formatoEntrada.parse(data);
        return formatoSaida.format(dataFormatada);
    }
    
    public int addCartao(Cartao cartao)  {
        int status;
        try {
            
            String validadeFormatada = formatarData(cartao.getValidade());
            
            String sql = "INSERT INTO cartoes (numero, nome_titular, validade, codigo_seguranca, usuario_id, tipo) VALUES (?, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(sql);

            st.setString(1, cartao.getNumero());
            st.setString(2, cartao.getNomeTitular());
            st.setString(3, validadeFormatada);
            st.setString(4, cartao.getCodigoSeguranca());
            st.setInt(5, cartao.getUsuario().getId());
            st.setString(6, cartao.getTipo());

            status = st.executeUpdate();
           
           if (status == 1) {
                return 1;
            } else {
                return 0;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir cartão: " + ex.getMessage());
            return ex.getErrorCode();
        } catch (ParseException e) {
        // Caso a data não seja válida
        System.out.println("Erro ao formatar a data: " + e.getMessage());
        e.printStackTrace();  // Imprime o erro completo no console
        return 0;  // Falha na formatação da data
     }
    }
    
    public Cartao buscarPorId(int id) {
        Cartao cartao = null;
        String sql = "SELECT * FROM cartoes WHERE id = ?";
        
        try {
                
            st = con.prepareStatement(sql);
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                cartao = new Cartao();
                cartao.setId(rs.getInt("id"));
                cartao.setNomeTitular(rs.getString("nome_titular"));
                cartao.setValidade(rs.getString("validade"));
                cartao.setCodigoSeguranca(rs.getString("codigo_seguranca"));
                cartao.setTipo(rs.getString("tipo"));
                // Preencher outros atributos conforme a tabela
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cartao;  // Retorna o usuário encontrado ou null caso não exista
    }
    
}
