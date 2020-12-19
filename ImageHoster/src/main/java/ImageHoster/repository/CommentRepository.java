package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {


    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;


    //retrieve all comments from database
    public List<Comment> getAllComments(Integer imageId, String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT co from Comment co where co.image.id =:imageId", Comment.class).setParameter("imageId", imageId);
            List<Comment> resultList = typedQuery.getResultList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();

            return null;

        }
    }

    //persist the comment through repository
    public Comment postComment(Comment comment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(comment);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;

    }


}
