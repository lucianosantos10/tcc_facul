
package br.com.flashcard.dao;

import br.com.flashcard.domain.Tema;
import br.com.flashcard.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TemaDAO {
    
    public boolean cadastrar(Tema t) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO temas ");
        sql.append("(tm_nome) ");
        sql.append("VALUES (?)");

        try {
            Connection conexao = ConnectionFactory.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            //mapeamento objeto relacional
            ps.setString(1, t.getTm_nome());
            ps.executeUpdate();
            System.out.println("Cadastro realizado com sucesso!");
            return true;

        } catch (SQLException ex) {
            System.out.println("Erro ao realizar o cadastro :" + ex);
            return false;

        }

    }
 
 
 
}



