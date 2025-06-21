
package br.com.flashcard.dao;

import br.com.flashcard.domain.Flashcard;
import br.com.flashcard.domain.Relatorio;
import br.com.flashcard.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RelatorioDAO {
    
     public boolean cadastrarAcerto(Flashcard f) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO relatorios ");
        sql.append("(rt_resultado, flashcards_fc_pergunta, temas_tm_nome) ");
        sql.append("VALUES ('acertou', ?, ?)");

        try {
            Connection conexao = ConnectionFactory.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            //mapeamento objeto relacional
            //o cadastrar é com base naquele tema e pergunta do flashcard
            //é f.getfc_pergunta e tema tambem 
            ps.setString(1, f.getFc_completa_pg());
            ps.setString(2, f.getFc_completa());
            ps.executeUpdate();
            System.out.println("Cadastro realizado com sucesso!");
            return true;

        } catch (SQLException ex) {
            System.out.println("ERRO ao realizar o cadastro :" + ex);
            return false;

        }
     }
      public boolean cadastrarCorreto() {
           //INSERT INTO `relatorios`(`rt_resultado`) VALUES ('acertou')
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO relatorios ");
        sql.append("(rt_resultado) ");
        sql.append("VALUES ('certo')");
        

        try {
            Connection conexao = ConnectionFactory.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            //mapeamento objeto relacional
            //o cadastrar é com base naquele tema e pergunta do flashcard
            //é f.getfc_pergunta e tema tambem 
            ps.execute();
            ps.executeQuery();
            ps.executeUpdate();
            System.out.println("Cadastro realizado com sucesso!");
            return true;

        } catch (SQLException ex) {
            System.out.println("ERRO ao realizar o cadastro :" + ex);
            return false;

        }
     }
        public boolean cadastrarErro(Relatorio r) {
            //INSERT INTO `relatorios`(`rt_resultado`) VALUES ('acertou')
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO relatorios ");
        sql.append("(rt_resultado, flashcards_fc_pergunta, temas_tm_nome) ");
        sql.append("VALUES ('errou', ?, ?)");

        try {
            Connection conexao = ConnectionFactory.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            //mapeamento objeto relacional
            ps.setString(1, r.getRt_pergunta());
            ps.setString(2, r.getRt_tema());
            ps.executeUpdate();
            
            ps.execute();
            ps.executeQuery();
            System.out.println("Cadastro realizado com sucesso!");
            return true;

        } catch (SQLException ex) {
            System.out.println("ERRO ao realizar o cadastro :" + ex);
            return false;

        }

    }
    
        
        /////////------////////
    
    public List<String> buscarPerguntasErradas(){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT r.rt_erro, f.fc_pergunta ");
      sql.append("FROM relatorios r ");
      sql.append("INNER JOIN flashcards f ON f.fc_id = r.flashcards_fc_id");
      
      try{
         Connection conexao = ConnectionFactory.conectar();
         
         PreparedStatement ps = conexao.prepareStatement(sql.toString()); 
         
         ResultSet resultado = ps.executeQuery();
          
         List<String> relatorios = new ArrayList<>();
         
         while(resultado.next()){
           
           Flashcard f = new Flashcard();
           f.setFc_pergunta(resultado.getString("f.fc_pergunta"));
           /*
           Relatorio r = new Relatorio();
           r.setRt_erro(resultado.getString("r.rt_erro"));
           r.setFlashcard(f);
           relatorios.add(r.getRt_erro());
         
           */
         }
         
         System.out.println("Busca realizada com sucesso!");
         return relatorios;
         
      }catch (SQLException ex) {
            System.out.println("ERRO ao realizar a busca :" + ex);
            return null;
           
        }
      
    }
    
    /*
    public ArrayList<Relatorio> buscarAcertoErroPorTema(){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT r.rt_acerto, r.rt_erro, t.tm_nome ");
      sql.append("FROM relatorios r ");
      sql.append("INNER JOIN temas t ON t.tm_id = r.temas_tm_id");
      
      try{
         Connection conexao = ConnectionFactory.conectar();
         
         PreparedStatement ps = conexao.prepareStatement(sql.toString()); 
         
         ResultSet resultado = ps.executeQuery();
          
         ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
         
         while(resultado.next()){
           
           Tema t = new Tema();
           t.setTm_nome(resultado.getString("t.tm_nome"));
           
           Relatorio r = new Relatorio();
           r.setRt_acerto(resultado.getLong("r.rt_acerto"));
           r.setRt_erro(resultado.getLong("r.rt_erro"));
           r.setTema(t);
           
           
           relatorios.add(r);
           
         }
         
         System.out.println("Busca realizada com sucesso!");
         return relatorios;
         
      }catch (SQLException ex) {
            System.out.println("ERRO ao realizar a busca :" + ex);
            return null;
           
        }
      
    }
    
    
     public ArrayList<Flashcard> buscarQtdPerguntasPorTema(){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT f.fc_pergunta, t.tm_nome ");
      sql.append("FROM flashcards f ");
      sql.append("INNER JOIN temas t ON t.tm_id = f.temas_tm_id");
      
      try{
         Connection conexao = ConnectionFactory.conectar();
         
         PreparedStatement ps = conexao.prepareStatement(sql.toString()); 
         
         ResultSet resultado = ps.executeQuery();
          
         ArrayList<Flashcard> busca = new ArrayList<Flashcard>();
         
         while(resultado.next()){
           
           Tema t = new Tema();
           t.setTm_nome(resultado.getString("t.tm_nome"));
           
           Flashcard f = new Flashcard();
           f.setFc_pergunta(resultado.getString("f.fc_pergunta"));
           f.setTema(t);
           
           
           busca.add(f);
           
         }
         
         System.out.println("Busca realizada com sucesso!");
         return busca;
         
      }catch (SQLException ex) {
            System.out.println("ERRO ao realizar a busca :" + ex);
            return null;
           
        }
      
    }
    
    */
    
    
    
}
