package lei.ibam.library.user.dto;

import jakarta.validation.constraints.*;

public class UserInputDto {


    @NotNull(message = "Veuillez Renseigner le Username")
    @NotBlank(message = "Le username ne doit pas etre vide")
    @Email(message = "Renseignez votre email")
    private String userInputName;

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

}

