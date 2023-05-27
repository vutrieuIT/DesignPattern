package com.example.edit.controllers;

import com.example.edit.Utils.ServletUtils;
import com.example.edit.beans.Articles;
import com.example.edit.beans.Category;
import com.example.edit.bridge.*;
import com.example.edit.bridge.impl.*;
import com.example.edit.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCateServlet", value = "/Admin/Category/*")
public class AdminCateServlet extends HttpServlet {

    private FeedbackBridge feedbackBridge = new FeedbackBridgeImpl();
    private CommentBridge commentBridge = new CommentBridgeImpl();
    private TagArticleBridge tagArticleBridge = new TagArticleBridgeImpl();
    private ArticleBridge articleBridge = new ArticleBridgeImpl();
    private CategoryBridge categoryBridge = new CategoryBridgeImpl();
    private EditorManageBridge editorManageBridge = new EditorManageBridgeImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                List<Category> list = CategoryModel.findAll();
                request.setAttribute("categories", list);
                ServletUtils.forward("/views/viewAdminCate/Index.jsp", request, response);
                break;
            case "/Detail/Delete":
                int id5 = 0;
                try {
                    id5 = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                List<Articles> listArt2 = ArticleModel.getArticleByCateId(id5);
                for(int i =0;i<listArt2.size();i++)
                {
                    feedbackBridge.DeleteFeedByAId(listArt2.get(i).getArticle_id());
                    commentBridge.DeleteCmtByArtId(listArt2.get(i).getArticle_id());
                    tagArticleBridge.DeleteTagByArt(listArt2.get(i).getArticle_id());
                }
                articleBridge.DeleteArtByIdCate(id5);
                categoryBridge.deleteCate(id5);
                String url = "/Admin/Category/Detail?id="+id5;
                ServletUtils.redirect(url,request,response);
                break;
            case "/Delete":
                int id = 0;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                List<Category> cateconList = categoryBridge.findCatCon(id);
                for(int j = 0;j<cateconList.size();j++)
                {
                    List<Articles> listArtCon = articleBridge.getArticleByCateId(cateconList.get(j).getCategories_id());
                    for(int t=0;t<listArtCon.size();t++)
                    {
                        commentBridge.DeleteCmtByArtId(listArtCon.get(t).getArticle_id());
                        tagArticleBridge.DeleteTagByArt(listArtCon.get(t).getArticle_id());
                        feedbackBridge.DeleteFeedByAId(listArtCon.get(t).getArticle_id());
                    }
                    editorManageBridge.DeleteByCate(cateconList.get(j).getCategories_id());
                    articleBridge.DeleteArtByIdCate(cateconList.get(j).getCategories_id());
                }
                List<Articles> listArt = articleBridge.getArticleByCateId(id);
                for(int i =0;i<listArt.size();i++)
                {
                    commentBridge.DeleteCmtByArtId(listArt.get(i).getArticle_id());
                    tagArticleBridge.DeleteTagByArt(listArt.get(i).getArticle_id());
                    feedbackBridge.DeleteFeedByAId(listArt.get(i).getArticle_id());
                }
                articleBridge.DeleteArtByIdCate(id);
                categoryBridge.deleteCateCon(id);
                editorManageBridge.DeleteByCate(id);
                categoryBridge.deleteCate(id);
                ServletUtils.redirect("/Admin/Category",request,response);
                break;
            case "/Add":
                ServletUtils.forward("/views/viewAdminCate/Add.jsp", request, response);
                break;

            case "/Update":
                int idCate = 0;
                try {
                    idCate = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }

                Category c = categoryBridge.findCateById(idCate);
                if (c != null) {
                    request.setAttribute("category", c);
                    ServletUtils.forward("/views/viewAdminCate/Update.jsp", request, response);
                } else {
                    ServletUtils.redirect("/Admin/Category", request, response);
                }
                break;
            case "/Detail":
                response.setContentType("text/html;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                int id3 = 0;
                try {
                    id3 = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                Category c3 = categoryBridge.findCateById(id3);
                List<Category> categoryList = categoryBridge.findAllIn();
                if (c3 != null) {
                    List<Category> listCon = categoryBridge.getCateChilds(id3);
                    request.setAttribute("categories", listCon);
                    request.setAttribute("categoryList", categoryList);
                    request.setAttribute("category", c3);
                    ServletUtils.forward("/views/viewAdminCate/Detail.jsp", request, response);
                } else {
                    ServletUtils.redirect("/Admin/Category", request, response);
                }
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Add":
                addCate(request, response);
                break;
            case "/Update":
                updateCate(request, response);
                break;
            case "/Detail":
                addCateCon(request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
    private void addCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");

        Category category = new Category(0,name,0);
        categoryBridge.addCate(category);
        ServletUtils.redirect("/Admin/Category",request,response);
    }
    private void addCateCon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int parent_id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(0,name,parent_id);
        categoryBridge.addCateCon(category);

        String url = "/Admin/Category/Detail?id=" +parent_id;
        ServletUtils.redirect(url,request,response);
    }
    private void updateCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("categories_id"));
        String name = request.getParameter("name");
        Category category = new Category(id,name,0);
        categoryBridge.updateCate(category);
        ServletUtils.redirect("/Admin/Category",request,response);
    }
}
