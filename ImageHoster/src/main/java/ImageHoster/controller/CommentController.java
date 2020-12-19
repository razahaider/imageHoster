package ImageHoster.controller;


import ImageHoster.model.Comment;

import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.time.Clock;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired(required = true)
    private CommentService commentService;

    @Autowired
    private ImageService imService;


    //‘/image/{imageId}/{imageTitle}/comments’ for creating a new comment.
    // After persisting the comment in the database, the controller logic must redirect to the same page
    // displaying all the details of that particular image
    //Redirect to ‘showImage()’ method in ‘ImageController’ class.
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable(value = "imageTitle") String title, @PathVariable(name = "imageId") Integer imageId, @RequestParam(value = "comment") String commentText, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggeduser");
        Image image = imService.getImage(imageId);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setText(commentText);
        Clock clock = Clock.systemUTC();

        LocalDate localDate
                = LocalDate.now(clock);
        comment.setCreatedDate(localDate);
        comment.setImage(image);

        model.addAttribute("comments", comment);
        commentService.postComment(comment);
        //redirect to showimage method through its mapping
        return "redirect:/images/" + image.getId() + "/" + image.getTitle();

    }
}
