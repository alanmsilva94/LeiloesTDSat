/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet rs;

    public ProdutosDAO(Connection conn) {
        this.conn = conn; // Atribui a conexão passada ao atributo da classe
    }

    public int cadastrarProduto(ProdutosDTO produto) {
        int status;
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            status = prep.executeUpdate();
            return status;

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
        }
        return listagem;
    }

    public void venderProduto(ProdutosDTO produtosDTO) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            prep.setInt(2, produtosDTO.getId());
            prep.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro ao alterar status: " + ex.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
        }
        return listagem;
    }

    public ProdutosDTO getProdutosDTO(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try {

            prep = this.conn.prepareStatement(sql);
            prep.setInt(1, id);
            rs = prep.executeQuery();

            ProdutosDTO produtosDTO = new ProdutosDTO();

            rs.next();
            produtosDTO.setId(id);

            produtosDTO.setNome(rs.getString("nome"));
            produtosDTO.setValor(rs.getInt("valor"));
            produtosDTO.setStatus(rs.getString("status"));

            return produtosDTO;

        } catch (Exception ex) {
            System.out.println("erro: " + ex.getMessage());
            return null;
        }
    }
}
