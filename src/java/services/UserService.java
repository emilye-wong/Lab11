/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import models.User;

/**
 *
 * @author emily
 */
class UserService {

    public User getUUID(String uuid) throws Exception {
        UserDB userDB = new UserDB();
        User UUID = userDB.getUUID(uuid);
        return UUID;
    }
}
