package com.example.edit.controllers;

import com.example.edit.Utils.ServletUtils;
import com.example.edit.beans.Articles;
import com.example.edit.beans.Category;
import com.example.edit.beans.Tag;
import com.example.edit.bridge.*;
import com.example.edit.bridge.impl.*;
import com.example.edit.models.ArticleModel;
import com.example.edit.models.CategoryModel;
import com.example.edit.models.TagModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/Home/*")
public class HomeServlet extends HttpServlet {
    private ArticleBridge articleBridge = new ArticleBridgeImpl();
    private TagBridge tagBridge = new TagBridgeImpl();
    private CategoryBridge categoryBridge = new CategoryBridgeImpl();
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                HttpSession session = request.getSession();
                List<Articles> listTop10CateNext = articleBridge.findTop10CateNext();
                List<Articles> listTop10Cate = articleBridge.findTop10Cate();
                List<Category> lisAllCate = categoryBridge.findAllIn();
                List<Category> list4cate  = categoryBridge.find4Cate();

                List<Tag> list = tagBridge.findByindex();

                // 10 bài nhiều nhất mọi chuyên mục
                List<Articles> listtop5 = articleBridge.findTop5();
                List<Articles> listtop10 = articleBridge.findTop10();

                // Tìm kiếm nổi bật nhất trong tuần
                Articles find1 = articleBridge.fin1();
                List<Articles> listtop4 = articleBridge.findTop4();
                List<Articles> listtop3 = articleBridge.findTop3();

                // Mới nhất mọi chuyên mục
                Articles find1New = articleBridge.find1New();
                List<Articles> listtop5New = articleBridge.findTop5New();
                List<Articles> listtop5NewNext = articleBridge.findTop5NewNext();


                session.setAttribute("list4cate",list4cate);
                session.setAttribute("lisAllCate",lisAllCate);
                session.setMaxInactiveInterval(6000);

                request.setAttribute("listtop3", listtop3);
                request.setAttribute("find1", find1);

                // Top nổi bật
                request.setAttribute("find1New", find1New);
                request.setAttribute("listtop5New", listtop5New);
                request.setAttribute("listtop5NewNext", listtop5NewNext);
                request.setAttribute("listtop4", listtop4);
                request.setAttribute("Day",getCurrentDate());
                request.setAttribute("listtop", listtop5);
                request.setAttribute("listtopnext", listtop10);
                request.setAttribute("tags", list);
                request.setAttribute("listAllCate", lisAllCate);
                request.setAttribute("list4cate",list4cate);
                request.setAttribute("list10Cate",listTop10Cate);
                request.setAttribute("list10CateNext",listTop10CateNext);
                ServletUtils.forward("/views/viewHome/Index.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
