package projectbackend.model.account;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_role")
public class Account_role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "accountName", referencedColumnName = "accountname")
    private Account account;

    @Column(name = "is_delete")
    private boolean isDelete;

    public Account_role() {
    }

    public Account_role(Role role, Account account, boolean isDelete) {
        this.role = role;
        this.account = account;
        this.isDelete = isDelete;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
