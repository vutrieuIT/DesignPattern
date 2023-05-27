package com.example.edit.controllers;

import com.example.edit.Utils.ServletUtils;
import com.example.edit.beans.Articles;
import com.example.edit.beans.Tag;
import com.example.edit.bridge.*;
import com.example.edit.bridge.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminHomeServlet", value = "/Admin/Home/*")
public class AdminHomeServlet extends HttpServlet {
    private TagBridge tagBridge = new TagBridgeImpl();
    private ArticleBridge articleBridge = new ArticleBridgeImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                int allArticle = articleBridge.getAllArticle();
                int allCate = articleBridge.getAllCateAdmin();
                int allTag = articleBridge.getAllTag();
                int allUser = articleBridge.getAllUser();
                List<Articles> listTop10CateNext = articleBridge.findTop10CateNext();
                List<Articles> listTop10Cate = articleBridge.findTop10Cate();
                List<Articles> listtop3 = articleBridge.findTop3Admin();
                List<Tag> list = tagBridge.findAll();
                List<Articles> listtop5 = articleBridge.findTop5();
                List<Articles> listtop10 = articleBridge.findTop10();
                List<Articles> listtop5NewinWeek = articleBridge.get5ArticleNewinWeek();
                List<Articles> listtop5NewinWeekNext = articleBridge.get5ArticleNewinWeekNext();
                request.setAttribute("list10Cate",listTop10Cate);
                request.setAttribute("list10CateNext",listTop10CateNext);
                request.setAttribute("listtop5NewinWeek", listtop5NewinWeek);
                request.setAttribute("listtop5NewinWeekNext", listtop5NewinWeekNext);
                request.setAttribute("listtop", listtop5);
                request.setAttribute("listtopnext", listtop10);
                request.setAttribute("listtop3", listtop3);
                request.setAttribute("allArticle", allArticle);
                request.setAttribute("allCate", allCate);
                request.setAttribute("allTag", allTag);
                request.setAttribute("allUser", allUser);
                request.setAttribute("tags", list);
                ServletUtils.forward("/views/viewAdminHome/Index.jsp", request, response);
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
