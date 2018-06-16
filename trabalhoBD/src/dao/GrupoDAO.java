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
import model.Grupo;
import config.Conexao;

/**
 *
 * @author edimar
 */
public class GrupoDAO {

    public boolean adicionar(Grupo objeto) { //alterar a classe do parâmetro
        try {
            String sql = "INSERT INTO grupo (nome) VALUES (?)"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setString(1, objeto.getNome()); // alterar o primeiro parâmetro indica a interrogação, começando em 1

            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean alterar(Grupo objeto) {
        try {
            String sql = " UPDATE grupo "
                    + "    SET nome = ? "
                    + "  WHERE codigo = ? "; //alterar tabela, atributos e chave primária

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);

            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setString(1, objeto.getNome());
            pstmt.setInt(2, objeto.getCodigo());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean excluir(Grupo grupo) {
        try {
            String sql = " DELETE FROM grupo WHERE codigo = ? "; //alterar a tabela e a chave primária no WHERE

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, grupo.getCodigo()); //alterar conforme a chave primária

            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Grupo> selecionar() {
        String sql = "SELECT codigo, nome FROM grupo ORDER BY nome"; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Grupo> lista = new ArrayList<>();

            while (rs.next()) {
                Grupo objeto = new Grupo(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setCodigo(rs.getInt("codigo")); //alterar
                objeto.setNome(rs.getString("nome"));  //alterar

                lista.add(objeto);
            }
            stmt.close();
            return lista;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //método só para testar
    public static void main(String[] args) {
        Grupo objeto = new Grupo(); //alterar
        objeto.setNome("Alimentícios"); //alterar

        GrupoDAO dao = new GrupoDAO(); //alterar
        dao.adicionar(objeto); //alterar
    }
}
