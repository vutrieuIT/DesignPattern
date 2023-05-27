package com.example.edit.controllers;

import com.example.edit.Utils.ServletUtils;
import com.example.edit.beans.*;
import com.example.edit.bridge.ArticleBridge;
import com.example.edit.bridge.CategoryBridge;
import com.example.edit.bridge.TagArticleBridge;
import com.example.edit.bridge.TagBridge;
import com.example.edit.bridge.impl.ArticleBridgeImpl;
import com.example.edit.bridge.impl.CategoryBridgeImpl;
import com.example.edit.bridge.impl.TagArticleBridgeImpl;
import com.example.edit.bridge.impl.TagBridgeImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;


@WebServlet(name = "PostServlet", value = "/Post/*")
@MultipartConfig(
        fileSizeThreshold = 2 * 1024 * 1024,
        maxFileSize = 50 * 1024 * 1024,
        maxRequestSize = 50 * 1024 * 1024
)

public class    PostServlet extends HttpServlet {
    private TagBridge tagBridge = new TagBridgeImpl();
    private CategoryBridge categoryBridge = new CategoryBridgeImpl();
    private ArticleBridge articleBridge = new ArticleBridgeImpl();
    private TagArticleBridge tagArticleBridge = new TagArticleBridgeImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }
        HttpSession session = request.getSession();
        User writer = (User) session.getAttribute("authUser");
        int writer_id = writer.getUserId();
        switch (path) {
            case "/Index":
                List<Category> listCat = categoryBridge.findAllCat();
                request.setAttribute("categories",listCat);
                List<Tag> listTag = tagBridge.findAll();
                request.setAttribute("tags",listTag);

                ServletUtils.forward("/views/viewPost/Index.jsp", request, response);
                break;
            case "/Waiting":
                List<Articles> listWaiting = articleBridge.findArticleByStatus2(101, writer_id);
                request.setAttribute("listWaiting",listWaiting);
                ServletUtils.forward("/views/viewPost/ListWaiting.jsp", request, response);
                break;
            case "/Draft":
                List<Articles> listDraft = articleBridge.findArticleByStatus2(104,writer_id);
                request.setAttribute("listDraft",listDraft);
                ServletUtils.forward("/views/viewPost/ListDraft.jsp", request, response);
                break;
            case "/Refused":
                List<Articles> listRefused = articleBridge.findArticleByStatus2(103,writer_id);
                request.setAttribute("listRefused",listRefused);
                ServletUtils.forward("/views/viewPost/ListRefused.jsp", request, response);
                break;
            case "/Published":
                List<Articles> listPublished = articleBridge.findArticleByStatus2(102, writer_id);
                request.setAttribute("listPublished",listPublished);
                ServletUtils.forward("/views/viewPost/ListPublished.jsp", request, response);
                break;
            case "/Update":
                int article_id = Integer.parseInt(request.getParameter("article_id"));
                int checkStatus = articleBridge.checkStatus(article_id);
                Articles article = articleBridge.findByIdAll(article_id);
                if(article == null){
                    ServletUtils.redirect("/Post", request, response);
                } else{
                    request.setAttribute("checkStatus",checkStatus);
                    request.setAttribute("article", article);
                    ServletUtils.forward("/views/viewPost/Update.jsp", request, response);
                }
                break;
            case "/Category":
                getArticleByCate(request,response);
                break;
            case "/Tag":
                getArticleByTag(request,response);
                break;
            case "/Pagging":
                paggingByCate(request,response);
                break;
            case "/Premium":
                getArticlePre(request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }
        switch (path) {
            case "/Index":
                postArticles(request, response);
                postTagArticle(request,response);
                break;
            case "/Update":
                int article_id = Integer.parseInt(request.getParameter("article_id"));
                int checkStatus = articleBridge.checkStatus(article_id);
                update(request,response);
                if (checkStatus == 3){
                    ServletUtils.redirect("/Post/Refused", request, response);
                }
                else if (checkStatus == 4){
                    ServletUtils.redirect("/Post/Draft", request, response);
                }
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int article_id = Integer.parseInt(request.getParameter("article_id"));
        Articles a = articleBridge.findByIdAll(article_id);

        String title = request.getParameter("title");
        int views = Integer.parseInt(request.getParameter("views"));
        String abstracts = request.getParameter("abstracts");
        String content = request.getParameter("content");
        String categoryName = a.getCategoryName();
        int categories_id = a.getCategories_id();
        boolean premium = a.isPremium();
        java.util.Date create_date = Date.valueOf(request.getParameter("create_date"));
        Date publish_date = (Date) a.getPublish_date();
        int writer_id = a.getWriter_id();
        int status_id = 104;
        String avatar = a.getAvatar();

        Articles articles = new Articles(article_id,title,create_date,publish_date,views,abstracts,content,categories_id,premium,writer_id,status_id,avatar,categoryName);
        articleBridge.updateNews(articles);

    }
    private void postTagArticle(HttpServletRequest request, HttpServletResponse response) {
        Articles art = articleBridge.findArtByMaxID();
        int article_id = art.getArticle_id();
        String[] tagValue = request.getParameterValues("value");
        int size = tagValue.length;
        for (int i=0 ;i<size;i++){
            Tag tag = tagArticleBridge.findTagByTagName(tagValue[i]);
            int tags_id = tag.getTags_id();

            Tags_articles t = new Tags_articles(tags_id,article_id,0);
            tagArticleBridge.addTagArticle(t);
        }
    }

    private void postArticles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        Date create_date = getCurrentDate();
        Date publish_date = Date.valueOf("1111-11-11");
        int views= 0;

        String abstracts = request.getParameter("abstracts");
        String content = request.getParameter("content");
        String categoryName = request.getParameter("name");
        Category category = categoryBridge.findCatByName(categoryName);
        int categories_id = category.getCategories_id();
        boolean premium = false;

        HttpSession session = request.getSession();
        User Author = (User) session.getAttribute("authUser");

        int writer_id = Author.getUserId();
        int status_id = 104;

        for (Part part : request.getParts()){
            if (part.getName().equals("avatar")) {
                String contentDisposition = part.getHeader("content-disposition");
                String[] items = contentDisposition.split(";");
                for (String s : items) {
                    String tmp = s.trim();
                    if (tmp.startsWith("filename")) {
                        int idx = tmp.indexOf("=") + 2;
                        String avatar = tmp.substring(idx, tmp.length() - 1);
                        String targetDir = this.getServletContext().getRealPath("image/Article/");
                        String destination = targetDir + avatar;
                        part.write(destination);

                        Articles a = new Articles(0,title,create_date,publish_date,views,abstracts,content,
                                categories_id,premium, writer_id,status_id,avatar,categoryName);
                        articleBridge.addNews(a);
                    }
                }
            }
        }

        ServletUtils.redirect("/Post",request,response);
    }

    private void getArticleByCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cid = request.getParameter("cid");
        int cateId = Integer.parseInt(cid);
        String indexPage = request.getParameter("index");
        if(indexPage==null)
        {
            indexPage="1";
        }
        int index =Integer.parseInt(indexPage);
        int indexNext = index+1;
        int indexPre = index-1;
        if (indexPre == 0)
        {
            indexPre = 1;
        }
        if(index ==  1)
        {
            index =0 ;
        }
        else
        {
            index = (index -1) * 6 ;
        }
        int count  = articleBridge.getTotalArtilceByCate(cateId);

        int endPage = count/6;
        if(count  % 6!=0 ) {
            endPage++;
        }
        if (indexNext > endPage)
        {
            indexNext = indexNext -1;
        }
        if (session.getAttribute("checkAccPre")== null)

        {
            List<Articles> listA = articleBridge.getArticleToPagging(cateId,index);
            request.setAttribute("list", listA);
        }
        else {
            boolean checkPreUser=  (boolean) session.getAttribute("checkAccPre");
            if(checkPreUser == true)
            {
                List<Articles> listA = articleBridge.getArticleToPaggingPre(cateId,index);
                request.setAttribute("list", listA);
            }
            else {
                List<Articles> listA = articleBridge.getArticleToPagging(cateId,index);
                request.setAttribute("list", listA);
            }
        }
        List<Articles> listOneTag = articleBridge.findTopCateTag(cateId);
        Articles listOne = articleBridge.findTopCate(cateId);
        boolean check = categoryBridge.checkCate(cateId);
        List<Articles> listT = articleBridge.getArticleByCateList3(cateId);
        // Tìm danh mục con
        List<Category> listC =categoryBridge.getCateChilds(cateId);
        // Lấy tên danh muc
        List<Category> listCa =categoryBridge.getCateByID(cateId);
        // Lấy tất cả danh mục
        List<Category> lisAllCate = categoryBridge.findAllIn();
        List<Category> list5cate  = categoryBridge.find5Cate();
        session.setAttribute("list5cate",list5cate);
        session.setAttribute("lisAllCate",lisAllCate);
        session.setMaxInactiveInterval(6000);

        request.setAttribute("listC", listC);
        request.setAttribute("listT", listT);
        request.setAttribute("listOne", listOne);
        request.setAttribute("listOneTag", listOneTag);
        request.setAttribute("check", check);
        request.setAttribute("listCa", listCa);
        request.setAttribute("tag", index);
        request.setAttribute("indexNext", indexNext);
        request.setAttribute("indexPre", indexPre);
        request.setAttribute("EndPage",endPage);
        ServletUtils.forward("/views/viewArticlePosts/Index.jsp", request, response);
    }
    private static Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new Date(today.getTime());
    }
    private void getArticleByTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session =request.getSession();
        String indexPage = request.getParameter("index");
        String tid = request.getParameter("tid");
        int tagId = Integer.parseInt(tid);
        if(indexPage==null)
        {
            indexPage="1";
        }
        int index =Integer.parseInt(indexPage);
        int indexNext = index+1;
        int indexPre = index-1;
        if (indexPre == 0)
        {
            indexPre = 1;
        }
        if(index ==  1)
        {
            index =0 ;
        }
        else
        {
            index = (index -1) * 6 ;
        }
        int count  = articleBridge.getTotalArtilceByTag(tagId);
        int endPage = count/6;
        if(count  % 6!=0 ) {
            endPage++;
        }
        if (indexNext > endPage)
        {
            indexNext = indexNext -1;
        }
        request.setAttribute("Day",getCurrentDate());
        if (session.getAttribute("checkAccPre")== null)

        {
            List<Articles> listA = articleBridge.getArticleByTag(tagId,index);
            request.setAttribute("listA", listA);
        }
        else {
            boolean checkPreUser=  (boolean) session.getAttribute("checkAccPre");
            if(checkPreUser == true)
            {
                List<Articles> listA = articleBridge.getArticleByTagToPre(tagId,index);
                request.setAttribute("listA", listA);
            }
            else {
                List<Articles> listA = articleBridge.getArticleByTag(tagId,index);
                request.setAttribute("listA", listA);
            }
        }
        List<Category> lisAllCate = categoryBridge.findAllIn();
        List<Category> list5cate  = categoryBridge.find5Cate();
        session.setAttribute("list5cate",list5cate);
        session.setAttribute("lisAllCate",lisAllCate);
        session.setMaxInactiveInterval(6000);
        request.setAttribute("tag", index);
        request.setAttribute("tid", tid);
        request.setAttribute("indexNext", indexNext);
        request.setAttribute("indexPre", indexPre);
        request.setAttribute("EndPage",endPage);
        ServletUtils.forward("/views/viewArticlePosts/SearchTag.jsp", request, response);
    }
    private void paggingByCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session =request.getSession();
        String cid = request.getParameter("cid");
        String indexPage = request.getParameter("index");
        int cids =Integer.parseInt(cid);
        if(indexPage==null)
        {
            indexPage="1";
        }
        int index =Integer.parseInt(indexPage);
        int indexNext = index+1;
        int indexPre = index-1;
        if (indexPre == 0)
        {
            indexPre = 1;
        }
        if(index ==  1)
        {
            index =0 ;
        }
        else
        {
            index = (index -1) * 6 ;
        }
        int count  = articleBridge.getTotalArtilceByCate(cids);
        if (indexNext > count)
        {
            indexNext = indexNext -1;
        }
        int endPage = count/6;
        if(count  % 6!=0 ) {
            endPage++;
        }
        if (indexNext > endPage)
        {
            indexNext = indexNext -1;
        }
        if (session.getAttribute("checkAccPre")== null)

        {
            List<Articles> listA = articleBridge.getArticleToPagging(cids,index);
            request.setAttribute("list", listA);
        }
        else {
            boolean checkPreUser=  (boolean) session.getAttribute("checkAccPre");
            if(checkPreUser == true)
            {
                List<Articles> listA = articleBridge.getArticleToPaggingPre(cids,index);
                request.setAttribute("list", listA);
            }
            else {
                List<Articles> listA = articleBridge.getArticleToPagging(cids,index);
                request.setAttribute("list", listA);
            }
        }
        Articles listOne = articleBridge.findTopCate(cids);
        List<Articles> listOneTag = articleBridge.findTopCateTag(cids);
        boolean check = categoryBridge.checkCate(cids);
        List<Articles> listT = articleBridge.getArticleByCateList3(cids);
        List<Category> listC =categoryBridge.getCateChilds(cids);
        List<Category> listCa =categoryBridge.getCateByID(cids);
        List<Category> lisAllCate = categoryBridge.findAllIn();
        List<Category> list5cate  = categoryBridge.find5Cate();
        session.setAttribute("list5cate",list5cate);
        session.setAttribute("lisAllCate",lisAllCate);
        session.setMaxInactiveInterval(6000);
        request.setAttribute("listC", listC);
        request.setAttribute("listT", listT);
        request.setAttribute("listOneTag",listOneTag);
        request.setAttribute("listOne", listOne);
        request.setAttribute("check", check);
        request.setAttribute("listCa", listCa);
        request.setAttribute("tag", index);
        request.setAttribute("indexNext", indexNext);
        request.setAttribute("indexPre", indexPre);
        request.setAttribute("EndPage",endPage);
        ServletUtils.forward("/views/viewArticlePosts/Index.jsp", request, response);
    }private void getArticlePre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String indexPage = request.getParameter("index");
        if(indexPage==null)
        {
            indexPage="1";
        }
        int index =Integer.parseInt(indexPage);
        int indexNext = index+1;
        int indexPre = index-1;
        if (indexPre == 0)
        {
            indexPre = 1;
        }
        if(index ==  1)
        {
            index =0 ;
        }
        else
        {
            index = (index -1) * 6 ;
        }
        int count  = articleBridge.getTotalArticlePre();
        if (indexNext > count)
        {
            indexNext = indexNext -1;
        }
        int endPage = count/6;
        if(count  % 6!=0 ) {
            endPage++;
        }
        if (indexNext > endPage)
        {
            indexNext = indexNext -1;
        }
        List<Articles> list = articleBridge.getArticlePre(index);
        List<Category> lisAllCate = categoryBridge.findAllIn();
        List<Category> list5cate  = categoryBridge.find5Cate();
        session.setAttribute("list5cate",list5cate);
        session.setAttribute("lisAllCate",lisAllCate);
        session.setMaxInactiveInterval(6000);
        request.setAttribute("list", list);
        request.setAttribute("tag", index);
        request.setAttribute("Day",getCurrentDate());
        request.setAttribute("indexNext", indexNext);
        request.setAttribute("indexPre", indexPre);
        request.setAttribute("EndPage",endPage);
        ServletUtils.forward("/views/viewArticlePosts/Prenium.jsp", request, response);
    }

}
