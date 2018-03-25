//package com.example.service;
//
//import com.example.domain.User;
//import com.example.web.dto.UserDto;
//
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//public interface UserService {
//
//    User registerNewUserAccount(UserDto accountDto);
//
//    User getUser(String verificationToken);
//
//    void saveRegisteredUser(User user);
//
//    void deleteUser(User user);
//
//    void createVerificationTokenForUser(User user, String token);
//
//    void createPasswordResetTokenForUser(User user, String token);
//
//    User findUserByEmail(String email);
//
//    User getUserByPasswordResetToken(String token);
//
//    User getUserByID(long id);
//
//    void changeUserPassword(User user, String password);
//
//    boolean checkIfValidOldPassword(User user, String password);
//
//    String validateVerificationToken(String token);
//
//    List<String> getUsersFromSessionRegistry();
//
//}
