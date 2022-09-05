package com.example.miniprojectee.manager;

import lombok.SneakyThrows;
import com.example.miniprojectee.models.Article;
import com.example.miniprojectee.models.User;
import com.example.miniprojectee.provider.DBConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ArticleManager {

    Logger logger = Logger.getLogger(UserManager.class.getName());
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final CommentManager commentManager = new CommentManager();
    @SneakyThrows
    public Article save(Article article) {
        String sql = "insert into articles " +
                "(title, content, user_id)" +
                " values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, article.getTitle());
        preparedStatement.setString(2, article.getContent());
        preparedStatement.setInt(3, article.getUserId());

        int execute = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int userId = generatedKeys.getInt(1);
        logger.info("New article created: " + article);
        article.setId(userId);
        return article;
    }


    @SneakyThrows
    public List<Article> articlesByAuthor(User author) {
        List<Article> articles = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("select * from articles where user_id = ?");
        statement.setInt(1, author.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
               Article article = Article.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .author(author)
                    .userId(author.getId())
                    .build();
            article.setComments(commentManager.commentsByArticle(article));
            articles.add(article);
        }
        return articles;
    }

    @SneakyThrows
    public List<Article> all() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT a.*, u.name AS author_name, u.surname AS author_surname " +
                "FROM articles a INNER JOIN users u ON a.user_id = u.id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Article article = Article.builder()
                    .id(resultSet.getInt("id"))
                    .title(resultSet.getString("title"))
                    .content(resultSet.getString("content"))
                    .author(User.builder()
                            .name(resultSet.getString("author_name"))
                            .surname(resultSet.getString("author_surname"))
                            .build())
                    .build();
            article.setComments(commentManager.commentsByArticle(article));
            articles.add(article);
        }
        return articles;
    }


    @SneakyThrows
    public void deleteArticle(int id,User user)  {
        PreparedStatement statement1 = connection.prepareStatement("DELETE FROM articles WHERE ID = ? and user_ID = ?");
        statement1.setInt(1, id);
        statement1.setInt(2, user.getId());
        int i = statement1.executeUpdate();
        if(i>0) System.out.println("Article deleted");
        else System.out.println("Wrong ID");

    }

    public Article findById(int id) {
     return null;
    }
}