/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gadolino2.model.DAO;

import br.com.gadolino2.model.bean.Cadastro;
import com.connection.ConnectionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor
 */
public class CadastroDAO {
    
    
      public Cadastro save(Cadastro cadastro) {
        EntityManager em = new ConnectionFactory().getConnection();
          

        try {
            em.getTransaction().begin();
            em.persist(cadastro);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return (Cadastro) cadastro;
    }
      
       public Cadastro update(Cadastro cadastro) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(cadastro);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return cadastro;
    }
      
     public List<Cadastro> FindAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Cadastro> cadastro = null;

        try {
            cadastro = em.createQuery("from cadastro p").getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + e);
        } finally {
            em.close();
        }

        return cadastro;
    } 
     
     public Cadastro remove(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Cadastro cadastro = null;
        try {
            cadastro = em.find(Cadastro.class, id);
            em.getTransaction().begin();
            em.remove(cadastro);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return cadastro;
    }
}