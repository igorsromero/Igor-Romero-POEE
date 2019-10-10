/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gadolino2.model.DAO;

import br.com.gadolino2.model.bean.Tipo;
import com.connection.ConnectionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor
 */
public class TipoDAO {
    
    
      public Tipo save(Tipo tipo) {
        EntityManager em = new ConnectionFactory().getConnection();
          

        try {
            em.getTransaction().begin();
            em.persist(tipo);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return (Tipo) tipo;
    }
      
       public Tipo update(Tipo tipo) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(tipo);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return tipo;
    }
      
     public List<Tipo> FindAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Tipo> tipo = null;

        try {
            tipo = em.createQuery("from tipo p").getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + e);
        } finally {
            em.close();
        }

        return tipo;
    } 
     
public Long findId() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            Query query = em.createQuery("SELECT Id FROM anime.tipo where p");
            return (Long) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     
     public Tipo remove(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Tipo tipo = null;
        try {
            tipo = em.find(Tipo.class, id);
            em.getTransaction().begin();
            em.remove(tipo);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return tipo;
    }
}