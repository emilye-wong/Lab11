package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {

    public boolean resetPassword(String email, String path, String url) {
        UserDB userDB = new UserDB();
        String uuid = UUID.randomUUID().toString();

        try {
            User user = userDB.get(email);
            if (email.equals(user.getEmail())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful reset to {0}", email);

                String to = user.getEmail();
                String subject = "NotesKeeper Password";
                String template = path + "/emailtemplates/resetpassword.html";

                String link = url + "?uuid=" + uuid;

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                tags.put("link", link);

                user.setResetPasswordUUID(uuid);
                userDB.update(user);

                GmailService.sendMail(to, subject, template, tags);
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean changePassword(String uuid, String password) {
        UserService us = new UserService();
        try {
            User user = us.getUUID(uuid);
            user.setUserPassword(password);
            user.setResetPasswordUUID(null);
            UserDB ur = new UserDB();
            ur.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);

                /*
                String body = "Successful login by " + user.getFirstName() + " on " + (new java.util.Date()).toString();
                GmailService.sendMail(email, "Successful Login", body, false);
                 */
 /*
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
                 */
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
