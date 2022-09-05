package com.example.miniprojectee.manager;

import lombok.SneakyThrows;
import com.example.miniprojectee.models.Article;
import com.example.miniprojectee.models.Comment;
import com.example.miniprojectee.models.User;
import com.example.miniprojectee.provider.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommentManager {

    Logger logger = Logger.getLogger(CommentManager.class.getName());
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    @SneakyThrows
    public Comment save(Comment comment, Article article) {
        String sql = "insert into comments " +
                "(content, author_ID, article_ID)" +
                " values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, comment.getContent());
        preparedStatement.setInt(2, comment.getUserId());
        preparedStatement.setInt(3, article.getId());

        int execute = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int commentId = generatedKeys.getInt(1);
        logger.info("New comment created: " + comment);
        comment.setId(commentId);
        return comment;
    }


    @SneakyThrows
    public List<Comment> commentsByArticle(Article article) {
        List<Comment> comments = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT c.*, u.name AS author_name," +
                " u.surname AS author_surname FROM comments c " +
                "INNER JOIN users u  ON c.author_id=u.id where c.article_id = ?");
        statement.setInt(1, article.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String content = resultSet.getString("content");
            int userId = resultSet.getInt("author_id");
            comments.add(Comment.builder()
                    .id(id)
                    .content(content)
                    .userId(userId)
                    .author(User.builder()
                            .id(userId)
                            .name(resultSet.getString("author_name"))
                            .surname(resultSet.getString("author_surname"))
                            .build())
                    .build());
        }
        return comments;
    }

    @SneakyThrows
    public void deleteFromMyArticles(Integer artId, Integer comId, User user) {
        PreparedStatement statement = connection.prepareStatement("DELETE c FROM comments c " +
                "INNER JOIN articles a ON a.ID = c.article_ID " +
                "WHERE c.ID = ? AND c.article_ID = ? AND (c.author_ID =? OR a.user_ID=?)");
        statement.setInt(1, comId);
        statement.setInt(2, artId);
        statement.setInt(3, user.getId());
        statement.setInt(4, user.getId());
        int i = statement.executeUpdate();
        if(i>0){
            System.out.println("Comment deleted");
        }else {
            System.out.println("You do not have access to delete the comment!");
        }
    }
}