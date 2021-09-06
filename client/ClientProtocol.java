package client;

import model.UserData;

public class ClientProtocol {
    private UserData userData;

    public ClientProtocol(UserData userData) {
        this.userData = userData;
    }

    public UserData setData(int stdNumber,String firstName, String surname, String faculty, String course, String degree){
        return this.userData;
    }
}
