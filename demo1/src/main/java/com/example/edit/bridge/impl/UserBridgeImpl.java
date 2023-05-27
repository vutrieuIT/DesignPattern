package com.example.edit.bridge.impl;

import com.example.edit.beans.User;
import com.example.edit.bridge.UserBridge;
import com.example.edit.models.UserModel;

import java.time.LocalDate;
import java.util.List;

public class UserBridgeImpl implements UserBridge {
    @Override
    public void add(User c) {
        UserModel.add(c);
    }

    @Override
    public void edit(String pass, String email) {
        UserModel.edit(pass, email);
    }

    @Override
    public User findByEmail(String email) {
        return UserModel.findByEmail(email);
    }

    @Override
    public User getNameUserByEmail(String email) {
        return UserModel.getNameUserByEmail(email);
    }

    @Override
    public boolean checkByUserName(String username) {
        return UserModel.checkByUserName(username);
    }

    @Override
    public User findByUsername(String username) {
        return UserModel.findByUsername(username);
    }

    @Override
    public User findById(int user_id) {
        return UserModel.findById(user_id);
    }

    @Override
    public void editUser(int user_id, String name, String email, LocalDate date_of_birth, String second_name) {
        UserModel.editUser(user_id, name, email, date_of_birth, second_name);
    }

    @Override
    public void editAcc(int user_id, String password, String username) {
        UserModel.editAcc(user_id, password, username);
    }

    @Override
    public void editAccPre(int user_id, LocalDate issue_at) {
        UserModel.editAccPre(user_id, issue_at);
    }

    @Override
    public String getOtp(String email) {
        return UserModel.getOtp(email);
    }

    @Override
    public boolean checkOtp(String otp, String email) {
        return UserModel.checkOtp(otp, email);
    }

    @Override
    public void sendMailToEmailConfirm(String email, String Content) {
        UserModel.sendMailToEmailConfirm(email, Content);
    }

    @Override
    public void sendMailToEmail(String email, String Content) {
        UserModel.sendMailToEmail(email,Content);
    }

    @Override
    public User getUserByEmail(String email) {
        return UserModel.getUserByEmail(email);
    }

    @Override
    public boolean checkEx(int user_id) {
        return UserModel.checkEx(user_id);
    }

    @Override
    public boolean checkAccPre(int user_id) {
        return UserModel.checkAccPre(user_id);
    }

    @Override
    public List<User> listPhongVien(int role_id) {
        return UserModel.listPhongVien(role_id);
    }

    @Override
    public List<User> listBTV() {
        return UserModel.listBTV();
    }

    @Override
    public User findCatBySecondName(String second_name) {
        return UserModel.findCatBySecondName(second_name);
    }

    @Override
    public List<User> findAll() {
        return UserModel.findAll();
    }

    @Override
    public void deleteUser(int user_id) {
        UserModel.deleteUser(user_id);
    }

    @Override
    public void updateUser(User c) {
        UserModel.updateUser(c);
    }

    @Override
    public void updateAccPre(int user_id, LocalDate issue_at) {
        UserModel.updateAccPre(user_id,issue_at);
    }
    @Override
    public void refuseAccPre(int user_id, LocalDate issue_at) {
        UserModel.refuseAccPre(user_id,issue_at);
    }

    @Override
    public User getUserById(int user_id) {
        return UserModel.getUserById(user_id);
    }

    @Override
    public List<User> findUserWaitingAccept() {
        return UserModel.findUserWaitingAccept();
    }

}
