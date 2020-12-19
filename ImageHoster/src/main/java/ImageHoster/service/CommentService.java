package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;


    //retrieve all ocmments from database
    public List<Comment> getAllComments(String title, Integer imageId) {
        return commentRepository.getAllComments(imageId, title);

    }


    //persist the comment through repository
    public Comment postComment(Comment comment) {
        return commentRepository.postComment(comment);
    }

}
