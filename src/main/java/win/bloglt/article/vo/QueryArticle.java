package win.bloglt.article.vo;

import win.bloglt.common.pages.Pages;

public class QueryArticle extends Pages {
    private int articleAuthorId;
    private String title;

    public int getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(int articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
