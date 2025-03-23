/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import br.com.senac.servicostreaming.model.Conteudo;
import br.com.senac.servicostreaming.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadastrarDao {
    
    PreparedStatement st;
    ResultSet rs;
    private Conexao conexao;
    private Connection con;
    
   public CadastrarDao() {
        this.conexao = new Conexao();
        this.con = (Connection) this.conexao.conectar();
    }
    
   public List<Conteudo> getConteudos(String titulo) {

        String sql = "SELECT * FROM conteudos WHERE titulo LIKE ?";  // A consulta SQL para obter todos os filmes
         
        try {
           
            st = this.con.prepareStatement(sql);
            
            st.setString(1, "%" + titulo + "%");
            
            rs = st.executeQuery();

            List<Conteudo> listaConteudos= new ArrayList<>();

            while (rs.next()) {
                Conteudo conteudo = new Conteudo();

                conteudo.setId(rs.getInt("id"));
                conteudo.setTipoConteudo(rs.getString("tipoconteudo"));
                conteudo.setTitulo(rs.getString("titulo"));
                conteudo.setQuantiEpisodios(rs.getInt("quanti_episodio_etc"));
                conteudo.setDataLancamento(rs.getString("data_lancamento"));
                conteudo.setAutor(rs.getString("autor"));
                conteudo.setDescricao(rs.getString("descricao"));
                
                listaConteudos.add(conteudo);
            }
            return listaConteudos;
            
        } catch (Exception e) {
            return null;}
 } 
   
   public String formatarData(String data) throws ParseException {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFormatada = formatoEntrada.parse(data);
        return formatoSaida.format(dataFormatada);
    }
   
   public int salvar(Conteudo c) {
        int status;
        try {
            // Formatar a data antes de salvar
            String dataFormatada = formatarData(c.getDataLancamento());

            st = con.prepareStatement("INSERT INTO conteudos VALUES(null,?,?,?,?,?,?)");
            
            st.setString(1, c.getTipoConteudo());
            st.setString(2, c.getTitulo());
            st.setInt(3, c.getQuantiEpisodios());
            st.setString(4, dataFormatada);
            st.setString(5, c.getAutor());
            st.setString(6, c.getDescricao());
           
            
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (ParseException e) {
            System.out.println("Erro ao formatar a data: " + e.getMessage());
            return 0; // ou outro código para indicar erro na conversão
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
   
   public Conteudo buscarPorId(int id) {
        Conteudo conteudo = null;
        String sql = "SELECT * FROM conteudos WHERE id = ?";
        
        try {
             
            st = con.prepareStatement(sql);
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                conteudo = new Conteudo();
                conteudo.setId(rs.getInt("id"));
                conteudo.setTitulo(rs.getString("titulo"));
                conteudo.setDescricao(rs.getString("descricao"));
                // Preencher outros atributos conforme a tabela
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conteudo;  // Retorna o conteúdo encontrado ou null caso não exista
    }
   
}
