package pl.piotrgluszek.client.connection;

import pl.piotrgluszek.client.model.Comment;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CommentManager {

    private static final String WEB_TARGET = "https://jsonplaceholder.typicode.com/";
    private static final String COMMENTS_PATH = "comments";

    public static Comment[] getAllComment() {

        Client client = ClientBuilder.newClient();
        Comment[] comments = client.target(WEB_TARGET)
                .path(COMMENTS_PATH)
                .request(MediaType.APPLICATION_JSON)
                .get(Comment[].class);
        client.close();
        return comments;
    }

    public static Comment getCommentById(Long commentId) {
        Client client = ClientBuilder.newClient();
        Comment comment = client.target(WEB_TARGET)
                .path(COMMENTS_PATH)
                .path(String.valueOf(commentId))
                .request(MediaType.APPLICATION_JSON)
                .get(Comment.class);
        client.close();
        return comment;
    }

    public static Comment[] getCommentsByPostId(Long postId) {
        Client client = ClientBuilder.newClient();
        Comment[] comments = client.target(WEB_TARGET)
                .path(COMMENTS_PATH)
                .queryParam("postId", postId)
                .request(MediaType.APPLICATION_JSON)
                .get(Comment[].class);
        client.close();
        return comments;
    }

    public static Long addComment(Comment comment) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(WEB_TARGET)
                .path(COMMENTS_PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(comment, MediaType.APPLICATION_JSON));
        Long commentId = response.readEntity(Comment.class).getId();
        return commentId;
    }
}
