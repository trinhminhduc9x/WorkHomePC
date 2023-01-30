package projectbackend.dto.account;


public class AccountDto {
    private String accountname;
    private String password;
    private boolean isDelete;

    public AccountDto() {
    }

    public AccountDto(String accountname, String password, boolean isDelete) {
        this.accountname = accountname;
        this.password = password;
        this.isDelete = isDelete;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

}
