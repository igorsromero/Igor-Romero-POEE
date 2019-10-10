/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gadolino2.model.DAO;

import br.com.gadolino2.model.bean.Voz;
import com.connection.ConnectionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor
 */
public class VozDAO {
    
    
      public Voz save(Voz voz) {
        EntityManager em = new ConnectionFactory().getConnection();
          

        try {
            em.getTransaction().begin();
            em.persist(voz);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return (Voz) voz;
    }
      
       public Voz update(Voz voz) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(voz);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return voz;
    }
      
     public List<Voz> FindAll(int inicio, int fim) {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Voz> voz = null;

        try {
            voz = em.createQuery("from voz p").getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + e);
        } finally {
            em.close();
        }

        return voz;
    } 
     
     public Voz remove(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Voz voz = null;
        try {
            voz = em.find(Voz.class, id);
            em.getTransaction().begin();
            em.remove(voz);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return voz;
    }
}