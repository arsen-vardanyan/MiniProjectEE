<%@ page import="com.example.miniprojectee.models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.miniprojectee.models.Article" %>
<%@ page import="com.example.miniprojectee.models.Comment" %>
<%--
    Created by IntelliJ IDEA.
    User: armen
    Date: 29.08.2022
    Time: 20:26
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/user-home.css">
    <script src="" defer></script>
    <title>My Page</title>
</head>
<body>
<main>

    <header>
        <div>
            <h4>My page:
                <% User currentUser = (User) request.getSession().getAttribute("currentUser");%>
                <%= currentUser.getName() %> <%= currentUser.getSurname() %>
            </h4>
        </div>

        <div>
            <form action="logout" method="get">
                <div class="inputs">
                    <button type="submit">Log out</button>
                </div>
            </form>
        </div>
    </header>

    <div class="add-article">
        <form id="addArticle" class="input-group" action="add-article" method="post">

            <div class="inputs"><label for="add_article_title">
                Title:
                <input type="text" class="input-field" name="add_article_title" id="add_article_title">
            </label></div>

            <div class="inputs"><label for="add_article_content"> Content:
                <textarea class="input-field" name="add_article_content" id="add_article_content">
                </textarea>
            </label></div>

            <button type="submit" class="submit-btn">Add Article</button>
        </form>
    </div>

    <div class="all-articles">
        <table frame="box" rules="all">
            <thead>
            <th>Tile</th>
            <th>Conent</th>
            <th>Author</th>
            <th>Comments</th>
            <th>Add Comment</th>
            </thead>
            <% List<Article> allArticles = ((List<Article>) request.getAttribute("allArticles")); %>
            <% for (int i = 0; i < allArticles.size(); i += 1) { %>
            <tr>
                <% Article article = allArticles.get(i); %>
                <td>
                    <%= allArticles.get(i).getTitle() %>
                </td>
                <td>
                    <%= allArticles.get(i).getContent() %>
                </td>
                <td>
                    <%= allArticles.get(i).getAuthor().getName() %>
                    <%= allArticles.get(i).getAuthor().getSurname() %>
                </td>
                <td>
                    <% for (int j = 0; j < article.getComments().size(); j += 1) { %>
                        <% Comment comment =  article.getComments().get(j); %>
                        <b><i>
                            <%= comment.getAuthor().getName() %>
                            <%= comment.getAuthor().getSurname() %>
                        </i></b><br>
                        <%= comment.getContent() %>
                    <br><br>
                    <% } %>
                </td>
                <td>
                    <form action="add-comment" method="post">
                        <div class="inputs">
                            <input style="visibility: hidden" name="addedArticleId" value="<%= allArticles.get(i).getId() %>">
                            <input name="addedCommentContent" placeholder="Comment">
                            <button type="submit">Add comment</button>
                        </div>
                    </form>
                </td>
                <td>
                    <form action="delete-article" method="post">
                        <div class="inputs">
                            <input style="visibility: hidden" name="deletedArticleId" value="<%= allArticles.get(i).getId() %>">
                            <button type="submit">Delete article</button>
                        </div>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </div>

</main>
</body>
</html>

<%--<menu>--%>
<%--    <div class="add-article">--%>
<%--        <form id="addArticle" class="input-group" action="add-article" method="post">--%>

<%--            <div class="input-group">--%>
<%--                <input type="text" class="input-field" name="add_article_title" id="add_article_title">--%>
<%--                <span class="highlight"></span>--%>
<%--                <span class="bar"></span>--%>
<%--                <label for="add_article_title">Title</label>--%>
<%--            </div>--%>

<%--            <div class="input-group">--%>
<%--                <textarea class="input-field" name="add_article_content" id="add_article_content"></textarea>--%>
<%--                <span class="highlight"></span>--%>
<%--                <span class="bar"></span>--%>
<%--                <label for="add_article_content">Content</label>--%>
<%--            </div>--%>

<%--            <div><button type="submit" class="submit-btn">Add Article</button></div>--%>
<%--        </form>--%>
<%--    </div>--%>

<%--    <div class="all-articles">--%>

<%--        <% List<Article> allArticles = ((List<Article>) request.getAttribute("allArticles")); %>--%>
<%--        <% for (int i = 0; i < allArticles.size(); i += 1) { %>--%>

<%--        <div class="posts">--%>
<%--            <div class="post">--%>
<%--                <% Article article =allArticles.get(i); %>--%>
<%--                <div class="section">--%>
<%--                    <div><h5>--%>
<%--                        <span>By</span> <%= allArticles.get(i).getAuthor().getName() %>--%>
<%--                    </h5></div>--%>

<%--                    <div><h3>--%>
<%--                        <%= allArticles.get(i).getTitle() %>--%>
<%--                    </h3></div>--%>

<%--                    <div><p>--%>
<%--                        <%= allArticles.get(i).getContent() %>--%>
<%--                    </p></div>--%>

<%--                    <div class="comments">--%>
<%--                        <% for (int j = 0; j < article.getComments().size(); j += 1) { %>--%>
<%--                        <% Comment comment =  article.getComments().get(j); %>--%>
<%--                        <%= comment.getContent()%>--%>
<%--                        <%= comment.getAuthor().getName() %>--%>
<%--                        <form action="add-comment" method="post">--%>
<%--                            <input style="visibility: hidden" name="addedArticleId" value="<%= allArticles.get(i).getId() %>">--%>
<%--                            <input name="addedCommentContent" placeholder="Comment">--%>
<%--                            <button type="submit">Add comment</button>--%>
<%--                        </form>--%>
<%--                        <%}%>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="button">--%>
<%--                    <form action="delete-article" method="post">--%>
<%--                        <input style="visibility: hidden" name="deletedArticleId" value="<%= allArticles.get(i).getId()%>">--%>
<%--                        <button type="submit">Delete article</button>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <% } %>--%>
<%--    </div>--%>
<%--</menu>--%>