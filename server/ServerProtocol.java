package server;

import model.UserData;

public class ServerProtocol {
    private UserData userData;

    public ServerProtocol() {
        this.userData = new UserData();
    }

    public void setUserData(UserData userData){
        this.userData.setStudentNumber(userData.getStudentNumber());
        this.userData.setStudentFirstName(userData.getStudentFirstName());
        this.userData.setStudentSurname(userData.getStudentSurname());
        this.userData.setStudentFaculty(userData.getStudentFaculty());
        this.userData.setStudentCourse(userData.getStudentCourse());
        this.userData.setStudentDegree(userData.getStudentDegree());
    }
}
