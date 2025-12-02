package it.unicam.cs.agricultural_platform.dto.content;

public class PasswordChangeRequestDTO {

    private long userId;
    private String oldPassword, newPassword;

    public PasswordChangeRequestDTO() {
    }

    public PasswordChangeRequestDTO(long userId, String oldPassword, String newPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public long getUserId(){return this.userId;}
    public void setUserId(long userId){this.userId = userId;}

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
