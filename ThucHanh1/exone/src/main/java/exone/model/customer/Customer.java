package exone.model.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;

import exone.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(columnDefinition = "DATE")
    private String birthday;

    private int gender;
    private String identifyCard;
    private  String phoneNumber;
    private  String email;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customerType", referencedColumnName = "id")
    private CustomerType customerType;

    private String address;

    @ManyToOne
    @JoinColumn(name = "user_name",referencedColumnName = "username")
    private User user;

}
