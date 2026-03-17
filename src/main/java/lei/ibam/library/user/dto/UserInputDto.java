package lei.ibam.library.user.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lei.ibam.library.user.model.Statut;

public class UserInputDto {

    @NotNull(message = "Veuillez Renseigner votre nom")
    @NotBlank(message = "Le nom ne doit pas etre vide")
    public String userFirstName;


    @NotNull(message = "Veuillez Renseigner votre prénom")
    @NotBlank(message = "Le prénom ne doit pas etre vide")
    public String userLastName;

    @NotNull(message = "Veuillez Renseigner le Username")
    @NotBlank(message = "Le username ne doit pas etre vide")
    @Email(message = "Renseignez votre email")
    public String userInputName;

    @NotNull(message = "Veuillez Renseigner le numéro de téléphone")
    public int userPhoneNumber;

    @NotNull(message = "veuillez renseigner le statut de l'utilisateur!!! ETUDIANT,ENSEIGNANT,PARTICULIER")
    @Enumerated(EnumType.STRING)
    public Statut userStatut;

    @NotNull(message = "Veuillez renseigner le mdp")
    @NotBlank(message = "Renseignez un mdp correct")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String passwordInput;

    public String getUserInputName() {
        return userInputName;
    }

    public void setUserInputName(String userInputName) {
        this.userInputName = userInputName;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }



    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(int userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Statut getUserStatut() {
        return userStatut;
    }

    public void setUserStatut(Statut userStatut) {
        this.userStatut = userStatut;
    }

}



