package com.example.edit.controllers;

import com.example.edit.Utils.ServletUtils;
import com.example.edit.beans.Category;
import com.example.edit.beans.Editor_manage_categories;
import com.example.edit.beans.User;
import com.example.edit.bridge.*;
import com.example.edit.bridge.impl.*;
import com.example.edit.models.CategoryModel;
import com.example.edit.models.EditorManageModel;
import com.example.edit.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUserEditServlet", value = "/Admin/User/EditCate/*")
public class AdminUserEditServlet extends HttpServlet {

    private EditorManageBridge editorManageBridge = new EditorManageBridgeImpl();
    private CategoryBridge categoryBridge = new CategoryBridgeImpl();
    private UserBridge userBridge = new UserBridgeImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/EditCate";
        }
        switch (path) {
            case "/EditCate":
                List<User> listBTV = userBridge.listBTV();
                request.setAttribute("list", listBTV);
                ServletUtils.forward("/views/viewAdminUser/EditCate.jsp",request,response);
                break;
            case "/Edit":
                int id5 = 0;
                try {
                    id5 = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                List<Editor_manage_categories> listCateById = editorManageBridge.listCateByIdUser(id5);
                List<Category> listCates = categoryBridge.findAllIn();
                request.setAttribute("listCateById",listCateById);
                request.setAttribute("listCate",listCates);
                ServletUtils.forward("/views/viewAdminUser/Edit.jsp",request,response);
                break;
            case "/Edit/Delete":
                int id6 = 0;
                int id7=0;
                try {
                    id7 = Integer.parseInt(request.getParameter("id"));
                    id6 = Integer.parseInt(request.getParameter("id_editmana"));
                } catch (NumberFormatException e) {
                }
                editorManageBridge.DeleteEditorManage(id6);
                String url="/Admin/User/EditCate/Edit?id="+id7;
                ServletUtils.redirect("/Edit"+url,request,response);
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
            case "/Edit":
                editBTVCate(request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
    private void editBTVCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int btv_id = Integer.parseInt(request.getParameter("id"));

        int id_cate = Integer.parseInt(request.getParameter("cate_id"));

        Editor_manage_categories c = new Editor_manage_categories(0,btv_id,id_cate);
        editorManageBridge.addBTVCate(c);

        String url="/Admin/User/EditCate/Edit?id="+btv_id;
        ServletUtils.redirect("/Edit"+url,request,response);
    }
}
