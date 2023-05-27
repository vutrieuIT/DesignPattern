package com.example.edit.controllers;

import com.example.edit.Utils.ServletUtils;
import com.example.edit.beans.*;
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

@WebServlet(name = "EditTagServlet", value = "/Editor/EditTag/*")
public class EditTagServlet extends HttpServlet {
    private ArticleBridge articleBridge = new ArticleBridgeImpl();
    private TagBridge tagBridge = new TagBridgeImpl();
    private TagArticleBridge tagArticleBridge = new TagArticleBridgeImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }
        switch (path) {
            case "/Index":
                int id3 = 0;
                try {
                    id3 = Integer.parseInt(request.getParameter("article_id"));
                } catch (NumberFormatException e) {
                }
                Articles c3 = articleBridge.findByIdAll(id3);
                List<Tag> listTag = articleBridge.findTagByArtIdKhoa(id3);
                List<Tag> listTagAll = tagBridge.findAll();
                request.setAttribute("article",c3);
                request.setAttribute("tags",listTag);
                request.setAttribute("listtag",listTagAll);
                ServletUtils.forward("/views/viewEditTag/Index.jsp", request, response);
                break;
            case "/Delete":
                int id4 = 0;
                int id5 = 0;
                try{
                    id5 = Integer.parseInt(request.getParameter("a_id"));
                    id4 = Integer.parseInt(request.getParameter("tags_id"));
                }
                catch (NumberFormatException e)
                {

                }
                tagArticleBridge.deleteTagArt(id4);
                String url = "/Editor/EditTag/Index?article_id="+id5;
                ServletUtils.redirect("/Edit"+url,request,response);
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

        switch (path) {
            case "/Index":
                addTags(request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
    private void addTags(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int article_id = Integer.parseInt(request.getParameter("article_id"));
        int tags_id = Integer.parseInt(request.getParameter("tags_id"));
        int index = 0;
        Tags_articles tagArticleModel = new Tags_articles(tags_id,article_id,index);
        tagArticleBridge.addTagArticle(tagArticleModel);
        String url = "/Editor/EditTag/Index?article_id="+article_id;
        ServletUtils.redirect("/Edit"+url,request,response);

    }

}