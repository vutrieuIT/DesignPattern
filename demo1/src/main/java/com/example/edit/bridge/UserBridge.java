package com.example.edit.bridge;

import com.example.edit.Utils.DbUtils;
import com.example.edit.beans.User;
import com.example.edit.models.UserModel;
import org.sql2o.Connection;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public interface UserBridge {
    static final String ACCOUNT = "baboga12@outlook.com";
    static final String PASSWORD = "Ngochai0612";
    static final String SUBJECT = "Quên mật khẩu.";
    static final String SUBJECTS = "Xác nhận thông tin.";

    void add(User c);
    // Reset mật khẩu qua Email
    void edit(String pass, String email);
    //Tìm User theo email
    User findByEmail(String email);

    //Lấy tài khoản theo email
    User getNameUserByEmail(String email);
    boolean checkByUserName(String username);

    //Tìm User theo Username
    User findByUsername(String username);

    //Tìm User theo Uid
    User findById(int user_id);

    //Chỉnh sửa thông tin User
    void editUser(int user_id, String name, String email, LocalDate date_of_birth, String second_name);

    //Chỉnh sửa Account User
    void editAcc(int user_id, String password, String username);

    //Thêm Account Pre
    void editAccPre(int user_id, LocalDate issue_at);

    // Lấy mã Otp
    String getOtp(String email);

    //Kiểm tra xem Otp có trùng không
    boolean checkOtp(String otp, String email);

    //Gửi mail cho người dùng xác nhận thông tin gia hạn tài khoản
    void sendMailToEmailConfirm(String email, String Content);

    // Gửi mail mã Otp cho khách hàng
    void sendMailToEmail(String email, String Content);

    //Lấy User theo Email
    User getUserByEmail(String email);

    // Kiểm tra Username có trùng không;

    // Kiểm tra tài khoản người dùng còn hạn hay không
    boolean checkEx(int user_id);

    // Check phải Account Pre ko
    boolean checkAccPre(int user_id);

    List<User> listPhongVien(int role_id);

    List<User> listBTV();

    User findCatBySecondName(String second_name);

    List<User> findAll();

    void deleteUser(int user_id);

    void updateUser(User c);

    void updateAccPre(int user_id,LocalDate issue_at );

    void refuseAccPre(int user_id,LocalDate issue_at );

    User getUserById(int user_id);

    List<User> findUserWaitingAccept();

}
