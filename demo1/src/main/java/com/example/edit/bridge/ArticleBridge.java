package com.example.edit.bridge;

import com.example.edit.beans.Articles;
import com.example.edit.beans.Comments;
import com.example.edit.beans.Tag;
import com.example.edit.beans.User;

import java.util.List;

public interface ArticleBridge {

    List<Articles> findAll();

    List<Articles> findDraftArticles();

    List<Articles> findDraftEditorManage(int user_id);

    List<Articles> findArticleByStatus2(int status_id, int writer_id);

    Articles findById(int article_id);

    Articles findByIdAll(int article_id);

    //Lấy id bài viết max
    Articles findArtByMaxID();

    //Lấy bài viết để phân trang theo Tag
    List<Articles> getArticleByTag(int tags_id, int index);

    List<Articles> getArticleByTagToPre(int tags_id, int index);

    //Lấy tổng số bài viết để phân trang theo Tag
    int getTotalArtilceByTag(int tags_id);

    List<Articles> findRand5SameCat(int article_id);

    List<Tag> findTagByArtId(int article_id);

    List<Tag> findTagByArtIdKhoa(int article_id);

    User findAuthor(int article_id);

    List<Comments> findComment(int article_id);

    int checkStatus(int article_id);

    //Lấy danh sách bài viết mà btv duyệt
    List<Articles> findListAgreeEditor(int editor_id);

    //Lấy danh sách bài viết mà btv từ chối
    List<Articles> findListRefuseEditor(int editor_id);

    // Lấy top 5  bài viết xem nhiều nhất
    List<Articles> findTop5();

    // Lấy top 10 bài viết xem nhiều nhất  kể từ 5 cái trên
    List<Articles> findTop10();
    //Tìm top 4 bài viết nổi bật nhất trong tuần kể từ top 3
    List<Articles> findTop4();

    //Tìm top 3 bài viết nổi bật nhất trong tuần kể từ top 1
    List<Articles> findTop3();

    Articles fin1();

    List<Articles> findTop3Admin();

    Articles find1New();

    // Top 4 bài viết mới nhất kể t bài 1
    List<Articles> findTop5New();
    // Top 1 bài viết nỗi bật nhất trong tuần qua

    List<Articles> get5ArticleNewinWeek();

    List<Articles> get5ArticleNewinWeekNext();

    //Top 5 bài viết mới nhất kể từ cái thứ 5
    List<Articles> findTop5NewNext();

    int getAllCate();

    int getAllArticle();

    int getAllTag();

    int getAllUser();

    int getAllCateAdmin();

    // Tương tự hàm dưới, có thêm list Tag
    List<Articles> findTopCateTag(int categories_id);

    Articles findTopCate(int categories_id);

    //Top 5 mỗi chuyên mục kể từ chuyên mục 1
    List<Articles> findTop10Cate();

    //Top 10 chuyên mục kể từ chuyên mục 5
    List<Articles> findTop10CateNext();

    // Tìm kiếm theo Search
    List<Articles> findSearch(String text);

    // Lấy tổng bài viết khi tìm kiếm từ khóa
    int getTotalArtilceBySearh(String text);

    // Lấy tất cả bài viết đã tìm kiếm xong chia thành 6 bài để phân trang
    List<Articles> findSearchPagging(String text, int index);

    List<Articles> findSearchPaggingToPre(String text, int index);

    // Lấy các bài viết theo id danh mục
    List<Articles> getArticleByCate(int categories_id);

    // Lấy tôổng số bài viết trong 1 danh mục
    int getTotalArtilceByCate(int categories_id);

    // Lấy các bài viết theo danh mục chọn ra 6 bài phân thành 1 trang
    List<Articles> getArticleToPagging(int categories_id, int index);

    List<Articles> getArticleToPaggingPre(int categories_id, int index);

    List<Articles> getArticleByCateList3(int categories_id);

    // Kiểm tra bài viết có thuộc dạng prenium hay không
    boolean checkPre(int article_id);

    List<Articles> getArticlePre(int index);

    int getTotalArticlePre();

    void addNews(Articles a);

    void deleteNews(int article_id);

    void updateNews(Articles a);

    List<Articles> findDraft();

    void UpdateDraft(int article_id);

    void DeleteArtByIdCate(int categories_id);

    List<Articles> getArticleByCateId(int categories_id);

    void DeleteArtByIdUser(int writer_id);

    List<Articles> findByUserId(int writer_id);
}
