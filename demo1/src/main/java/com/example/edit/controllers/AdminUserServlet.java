package com.example.edit.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.edit.Utils.ServletUtils;
import com.example.edit.beans.Articles;
import com.example.edit.beans.Category;
import com.example.edit.beans.Editor_manage_categories;
import com.example.edit.beans.User;
import com.example.edit.bridge.*;
import com.example.edit.bridge.impl.*;
import com.example.edit.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "AdminUserServlet", value = "/Admin/User/*")
public class AdminUserServlet extends HttpServlet {
    private TagArticleBridge tagArticleBridge = new TagArticleBridgeImpl();
    private FeedbackBridge feedbackBridge = new FeedbackBridgeImpl();
    private CommentBridge commentBridge = new CommentBridgeImpl();

    private ArticleBridge articleBridge = new ArticleBridgeImpl();
    private EditorManageBridge editorManageBridge = new EditorManageBridgeImpl();
    private CategoryBridge categoryBridge = new CategoryBridgeImpl();
    private UserBridge userBridge = new UserBridgeImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                List<User> list = userBridge.findAll();
                request.setAttribute("list", list);
                ServletUtils.forward("/views/viewAdminUser/Index.jsp", request, response);
                break;
            case "/Delete":
                int id = 0;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                List<Articles> articlesList = articleBridge.findByUserId(id);
                for(int i=0;i<articlesList.size();i++)
                {
                    tagArticleBridge.DeleteTagByArt(articlesList.get(i).getArticle_id());
                    commentBridge.DeleteCmtByArtId(articlesList.get(i).getArticle_id());
                    feedbackBridge.DeleteFeedByAId(articlesList.get(i).getArticle_id());
                }
                articleBridge.DeleteArtByIdUser(id);
                commentBridge.DeleteCmtByUserId(id);
                editorManageBridge.DeleteByUserId(id);
                userBridge.deleteUser(id);
                ServletUtils.redirect("/Admin/User",request,response);
                break;
            case "/Update":
                int id2 = 0;
                try {
                    id2 = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                User u = userBridge.findById(id2);
                if (u != null) {
                    request.setAttribute("Users", u);
                    ServletUtils.forward("/views/viewAdminUser/Update.jsp", request, response);
                } else {
                    ServletUtils.redirect("/Admin/User", request, response);
                }
                break;
            case "/Detail":
                int id3 = 0;
                try {
                    id3 = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }

                User u2 = userBridge.findById(id3);
                if (u2 != null) {
                    request.setAttribute("Users", u2);
                    ServletUtils.forward("/views/viewAdminUser/Detail.jsp", request, response);
                } else {
                    ServletUtils.redirect("/Admin/User", request, response);
                }
                break;
            case "/Account":
                List<User> lists = userBridge.findUserWaitingAccept();
                request.setAttribute("list", lists);
                ServletUtils.forward("/views/viewAdminUser/Account.jsp",request,response);
                break;
            case "/Account/Extend":
                int id4 = 0;
                try {
                    HttpSession session = request.getSession();
                    id4 = Integer.parseInt(request.getParameter("id"));
                    LocalDate date = LocalDate.now();
                    userBridge.updateAccPre(id4,date);
                    User user = userBridge.getUserById(id4);
                    int Exp = user.getUserId();
                    String email = user.getEmail();
                    StringBuilder content = new StringBuilder();
                    content.append("Dear ").append(user.getName()).append("\n");
                    content.append("Cảm ơn khách hàng đã gia hạn tài khoản của mình. Với email ").append(email).append(". Chúng tôi đã kiểm tra giao dịch. Với số tài khoản ")
                            .append("\n");
                    content.append("Tài khoản của quý được gia hạn 7 ngày để từ ngày gia hạn ").append(getCurrentDate()).append("\n");
                    content.append("Nếu có gì thắc mắc xin vui lòng liên hệ Email: ngochai06122002@gmail.com");
//                    if (userBridge.checkEx(Exp) == true) {
//                        session.setAttribute("checkEx", true);
//                    } else {
//                        session.setAttribute("checkEx", false);
//                    }
//
//                    if (userBridge.checkAccPre(Exp) == true) {
//                        session.setAttribute("checkAccPre", true);
//                    } else {
//                        session.setAttribute("checkAccPre", false);
//                    }
//                    session.setAttribute("auth", true);
//                    session.setAttribute("authUser", user);
                    UserModel.sendMailToEmailConfirm(email, content.toString());
                    ServletUtils.forward("/Admin/User/Account",request,response);
                } catch (NumberFormatException e) {

                }
                break;
            case "/Account/Refuse":
                int id9 = 0;
                try {
                    id9 = Integer.parseInt(request.getParameter("id"));
                    HttpSession session = request.getSession();
                    LocalDate date1 = LocalDate.now();
                    UserModel.refuseAccPre(id9,date1);
                    User user = UserModel.getUserById(id9);
                    int Ex = user.getUserId();
                    String email1 = user.getEmail();
                    StringBuilder content1 = new StringBuilder();
                    content1.append("Dear ").append(user.getName()).append("\n");
                    content1.append("Xin lỗi khách hàng với email ").append(email1).append(". Chúng tôi đã kiểm tra giao dịch. Với số tài khoản ")
                            .append("\n");
                    content1.append("Tài khoản của quý khách chưa được xác nhận chuyển tiền ").append(getCurrentDate()).append("\n");
                    content1.append("Nếu có gì thắc mắc xin vui lòng liên hệ Email: ngochai06122002@gmail.com");
//                    if (userBridge.checkEx(Ex) == true) {
//                        session.setAttribute("checkEx", true);
//                    } else {
//                        session.setAttribute("checkEx", false);
//                    }
//
//                    if (userBridge.checkAccPre(Ex) == true) {
//                        session.setAttribute("checkAccPre", true);
//                    } else {
//                        session.setAttribute("checkAccPre", false);
//                    }
//                    session.setAttribute("auth", true);
//                    session.setAttribute("authUser", user);
                    UserModel.sendMailToEmailConfirm(email1, content1.toString());
                    ServletUtils.forward("/Admin/User/Account",request,response);
                } catch (NumberFormatException e) {

                }
                break;

            case "/Add":
                ServletUtils.forward("/views/viewAdminUser/Add.jsp",request,response);
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
                addUser(request, response);
                break;
            case "/Update":
                updateUser(request, response);
                break;
            case "/EditCate/Edit":
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


        ServletUtils.redirect("/Admin/User",request,response);
    }
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String rawpwd = request.getParameter("password");

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, rawpwd.toCharArray());

        String strDate = request.getParameter("birthday");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthday = LocalDate.parse(strDate, df);

        String strIssue = request.getParameter("issue_at");
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate issue_at = LocalDate.parse(strDate, df);


        String second_name = request.getParameter("secondname");
        String username = request.getParameter("username");

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        int role_id = Integer.parseInt(request.getParameter("role_id"));

        int otpExp = (int) Math.floor(((Math.random() * 899999) + 100000));
        int expiration = 7;

        boolean premium = request.getParameter("premium")!=null;

        User user = new User(0,username,bcryptHashString,name,issue_at,expiration,role_id,second_name,birthday,email,otpExp,otpExp,premium);
        userBridge.add(user);
        ServletUtils.redirect("/Admin/User",request,response);
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String rawpwd = request.getParameter("password");

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, rawpwd.toCharArray());

        String strDate = request.getParameter("birthday");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthday = LocalDate.parse(strDate, df);

        String strIssue = request.getParameter("issue_at");
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate issue_at = LocalDate.parse(strDate, df);

        int user_id = Integer.parseInt(request.getParameter("id"));

        String second_name = request.getParameter("secondname");
        String username = request.getParameter("username");

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        int role_id = Integer.parseInt(request.getParameter("role_id"));

        int otpExp = (int) Math.floor(((Math.random() * 899999) + 100000));
        int expiration = 7;

        boolean premium = request.getParameter("premium")!=null;

        User user = new User(user_id,username,bcryptHashString,name,issue_at,expiration,role_id,second_name,birthday,email,otpExp,otpExp,premium);
        userBridge.updateUser(user);
        ServletUtils.redirect("/Admin/User",request,response);
    }
    public static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
}
