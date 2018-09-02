package pl.piotrgluszek.client;

import pl.piotrgluszek.client.connection.CommentManager;
import pl.piotrgluszek.client.model.Comment;

import static pl.piotrgluszek.client.connection.CommentManager.addComment;
import static pl.piotrgluszek.client.connection.CommentManager.getCommentById;

public class ClientDemo {

    public static void main(String[] args) {

        // GET /comments
        Comment[] allComments = CommentManager.getAllComment();
        for (Comment comment : allComments) {
            System.out.println(comment.getId() + ": " + comment.getBody());
        }
        // GET /comments/1
        Comment commentNo1 = getCommentById(1L);
        System.out.println(commentNo1.getId() + ": " + commentNo1.getBody());

        // GET /comments ? postId = 1
        Comment[] postNo1Comments = CommentManager.getCommentsByPostId(1L);
        for (Comment comment : postNo1Comments) {
            System.out.println(comment.getId() + ": " + comment.getBody());
        }
        // POST /posts/1/comments
        Comment comment = new Comment();
        comment.setPostId(1L);
        comment.setName("COMMENT NAME");
        comment.setEmail("email@email.emial");
        comment.setBody("Text of the comment.");
        Long commentId = addComment(comment);
        System.out.println(commentId);

    }
}
