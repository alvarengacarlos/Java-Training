package integration.alvarengacarlos.customer;

import alvarengacarlos.customer.AccountRepositoryImpl;
import alvarengacarlos.customer.CreateAccountUseCase;
import alvarengacarlos.exeption.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CreateAccountUseCase")
public class CreateAccountUseCaseTests {
    private CreateAccountUseCase createAccountUseCase;

    @BeforeEach
    void beforeEach() {
        createAccountUseCase = new CreateAccountUseCase(new AccountRepositoryImpl());
    }

    @Test
    @DisplayName("should create an account")
    void shouldCreateAnAccount() throws ApplicationException {
        var input = new CreateAccountUseCase.CreateAccountDtoInput("John Doe", "johndoe@email.com", "johndoe@123");

        var output = createAccountUseCase.execute(input);

        assertEquals(output.email(), input.email());
        assertEquals(output.name(), input.name());
    }
}
