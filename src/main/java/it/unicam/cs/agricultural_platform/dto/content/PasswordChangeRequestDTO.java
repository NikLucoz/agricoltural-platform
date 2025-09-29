package it.unicam.cs.agricultural_platform.dto.content;

public class PasswordChangeRequestDTO {
    private String oldPassword, newPassword;

    public PasswordChangeRequestDTO() {
    }

    public PasswordChangeRequestDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
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
