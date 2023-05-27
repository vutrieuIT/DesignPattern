package com.example.edit.bridge.impl;

import com.example.edit.beans.Articles;
import com.example.edit.beans.Comments;
import com.example.edit.beans.Tag;
import com.example.edit.beans.User;
import com.example.edit.bridge.ArticleBridge;
import com.example.edit.models.ArticleModel;

import java.util.List;

public class ArticleBridgeImpl implements ArticleBridge {
    
    @Override
    public List<Articles> findAll() {
        return ArticleModel.findAll();
    }

    @Override
    public List<Articles> findDraftArticles() {
        return ArticleModel.findDraftArticles();
    }

    @Override
    public List<Articles> findDraftEditorManage(int user_id) {
        return ArticleModel.findDraftEditorManage(user_id);
    }

    @Override
    public List<Articles> findArticleByStatus2(int status_id, int writer_id) {
        return ArticleModel.findArticleByStatus2(status_id,writer_id);
    }

    @Override
    public Articles findById(int article_id) {
        return ArticleModel.findById(article_id);
    }

    @Override
    public Articles findByIdAll(int article_id) {
        return ArticleModel.findByIdAll(article_id);
    }

    @Override
    public Articles findArtByMaxID() {
        return ArticleModel.findArtByMaxID();
    }

    @Override
    public List<Articles> getArticleByTag(int tags_id, int index) {
        return ArticleModel.getArticleByTag(tags_id, index);
    }

    @Override
    public List<Articles> getArticleByTagToPre(int tags_id, int index) {
        return ArticleModel.getArticleByTagToPre(tags_id,index);
    }

    @Override
    public int getTotalArtilceByTag(int tags_id) {
        return ArticleModel.getTotalArtilceByTag(tags_id);
    }

    @Override
    public List<Articles> findRand5SameCat(int article_id) {
        return ArticleModel.findRand5SameCat(article_id);
    }

    @Override
    public List<Tag> findTagByArtId(int article_id) {
        return ArticleModel.findTagByArtId(article_id);
    }

    @Override
    public List<Tag> findTagByArtIdKhoa(int article_id) {
        return ArticleModel.findTagByArtIdKhoa(article_id);
    }

    @Override
    public User findAuthor(int article_id) {
        return ArticleModel.findAuthor(article_id);
    }

    @Override
    public List<Comments> findComment(int article_id) {
        return ArticleModel.findComment(article_id);
    }

    @Override
    public int checkStatus(int article_id) {
        return ArticleModel.checkStatus(article_id);
    }

    @Override
    public List<Articles> findListAgreeEditor(int editor_id) {
        return ArticleModel.findListAgreeEditor(editor_id);
    }

    @Override
    public List<Articles> findListRefuseEditor(int editor_id) {
        return ArticleModel.findListRefuseEditor(editor_id);
    }

    @Override
    public List<Articles> findTop5() {
        return ArticleModel.findTop5();
    }

    @Override
    public List<Articles> findTop10() {
        return ArticleModel.findTop10();
    }

    @Override
    public List<Articles> findTop4() {
        return ArticleModel.findTop4();
    }

    @Override
    public List<Articles> findTop3() {
        return ArticleModel.findTop3();
    }

    @Override
    public Articles fin1() {
        return ArticleModel.fin1();
    }

    @Override
    public List<Articles> findTop3Admin() {
        return ArticleModel.findTop3Admin();
    }

    @Override
    public Articles find1New() {
        return ArticleModel.find1New();
    }

    @Override
    public List<Articles> findTop5New() {
        return ArticleModel.findTop5New();
    }

    @Override
    public List<Articles> get5ArticleNewinWeek() {
        return ArticleModel.get5ArticleNewinWeek();
    }

    @Override
    public List<Articles> get5ArticleNewinWeekNext() {
        return ArticleModel.get5ArticleNewinWeekNext();
    }

    @Override
    public List<Articles> findTop5NewNext() {
        return ArticleModel.findTop5NewNext();
    }

    @Override
    public int getAllCate() {
        return ArticleModel.getAllCate();
    }

    @Override
    public int getAllArticle() {
        return ArticleModel.getAllArticle();
    }

    @Override
    public int getAllTag() {
        return ArticleModel.getAllTag();
    }

    @Override
    public int getAllUser() {
        return ArticleModel.getAllUser();
    }

    @Override
    public int getAllCateAdmin() {
        return ArticleModel.getAllCateAdmin();
    }

    @Override
    public List<Articles> findTopCateTag(int categories_id) {
        return ArticleModel.findTopCateTag(categories_id);
    }

    @Override
    public Articles findTopCate(int categories_id) {
        return ArticleModel.findTopCate(categories_id);
    }

    @Override
    public List<Articles> findTop10Cate() {
        return ArticleModel.findTop10Cate();
    }

    @Override
    public List<Articles> findTop10CateNext() {
        return ArticleModel.findTop10CateNext();
    }

    @Override
    public List<Articles> findSearch(String text) {
        return ArticleModel.findSearch(text);
    }

    @Override
    public int getTotalArtilceBySearh(String text) {
        return ArticleModel.getTotalArtilceBySearh(text);
    }

    @Override
    public List<Articles> findSearchPagging(String text, int index) {
        return ArticleModel.findSearchPagging(text,index);
    }

    @Override
    public List<Articles> findSearchPaggingToPre(String text, int index) {
        return ArticleModel.findSearchPaggingToPre(text,index);
    }

    @Override
    public List<Articles> getArticleByCate(int categories_id) {
        return ArticleModel.getArticleByCate(categories_id);
    }

    @Override
    public int getTotalArtilceByCate(int categories_id) {
        return ArticleModel.getTotalArtilceByCate(categories_id);
    }

    @Override
    public List<Articles> getArticleToPagging(int categories_id, int index) {
        return ArticleModel.getArticleToPagging(categories_id, index);
    }

    @Override
    public List<Articles> getArticleToPaggingPre(int categories_id, int index) {
        return ArticleModel.getArticleToPaggingPre(categories_id,index);
    }

    @Override
    public List<Articles> getArticleByCateList3(int categories_id) {
        return ArticleModel.getArticleByCateList3(categories_id);
    }

    @Override
    public boolean checkPre(int article_id) {
        return ArticleModel.checkPre(article_id);
    }

    @Override
    public List<Articles> getArticlePre(int index) {
        return ArticleModel.getArticlePre(index);
    }

    @Override
    public int getTotalArticlePre() {
        return ArticleModel.getTotalArticlePre();
    }

    @Override
    public void addNews(Articles a) {
        ArticleModel.addNews(a);
    }

    @Override
    public void deleteNews(int article_id) {
        ArticleModel.deleteNews(article_id);
    }

    @Override
    public void updateNews(Articles a) {
        ArticleModel.updateNews(a);
    }

    @Override
    public List<Articles> findDraft() {
        return ArticleModel.findDraft();
    }

    @Override
    public void UpdateDraft(int article_id) {
        ArticleModel.UpdateDraft(article_id);
    }

    @Override
    public void DeleteArtByIdCate(int categories_id) {
        ArticleModel.DeleteArtByIdCate(categories_id);
    }

    @Override
    public List<Articles> getArticleByCateId(int categories_id) {
        return ArticleModel.getArticleByCateId(categories_id);
    }

    @Override
    public void DeleteArtByIdUser(int writer_id) {
        ArticleModel.DeleteArtByIdUser(writer_id);
    }

    @Override
    public List<Articles> findByUserId(int writer_id) {
        return ArticleModel.findByUserId(writer_id);
    }
}
