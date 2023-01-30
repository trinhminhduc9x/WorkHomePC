package projectbackend.dto.account;

public class UpdatePasswordAccountDto {
    private String accountname;
    private String password;
    private String newPassword;

    public UpdatePasswordAccountDto() {
    }

    public UpdatePasswordAccountDto(String accountname, String password, String newPassword) {
        this.accountname = accountname;
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
