/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;
import config.Conexao;

/**
 *
 * @author edimar
 */
public class PessoaDAO {

    public boolean adicionar(Pessoa objeto) { //alterar a classe do parâmetro
        try {
            String sql = "INSERT INTO pessoa (nome, email, cpf) VALUES (?, ?, ?)"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setString(1, objeto.getNome()); // alterar o primeiro parâmetro indica a interrogação, começando em 1
            pstmt.setString(2, objeto.getEmail());
            pstmt.setLong(3, objeto.getCpf());
            
            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean alterar(Pessoa objeto) {
        try {
            String sql = " UPDATE pessoa "
                    + "    SET nome = ? "
                    + "    SET email = ? "
                    + "    SET cpf = ? "
                    + "  WHERE id = ? "; //alterar tabela, atributos e chave primária

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);

            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setString(1, objeto.getNome());
            pstmt.setString(2, objeto.getEmail());
            pstmt.setLong(3, objeto.getCpf());
            
            pstmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean excluir(Pessoa objeto) {
        try {
            String sql = " DELETE FROM pessoa WHERE id = ? "; //alterar a tabela e a chave primária no WHERE

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            //alterar conforme a chave primária
            pstmt.setInt(1, objeto.getId());

            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Pessoa> selecionar() {
        String sql = "SELECT id, nome, email, cpf FROM pessoa ORDER BY nome"; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Pessoa> lista = new ArrayList<>();

            while (rs.next()) {
                Pessoa objeto = new Pessoa(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setId(rs.getInt("id")); //alterar
                objeto.setNome("nome");
                objeto.setEmail("email");
                objeto.setCpf("cpf");
                lista.add(objeto);
            }
            stmt.close();
            return lista;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
