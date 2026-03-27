package lei.ibam.library.view.userView.model;

import jakarta.persistence.*;
import lei.ibam.library.user.model.Statut;
import org.hibernate.annotations.Immutable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users_view")
@Immutable
public class UserView {

    @Id
    @Column(name = "userId")
    Long userId;


    private String userName;
    private  String firstName;
    private String lastName;
    private int phoneNumber;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Statut getStatut() {
        return statut;
    }

    @Enumerated(EnumType.STRING)
    public void setStatut(Statut statut) {
        this.statut = statut;
    }



}
