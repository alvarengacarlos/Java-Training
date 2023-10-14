package unit.alvarengacarlos.customer;

import alvarengacarlos.customer.Account;
import alvarengacarlos.customer.AccountRepository;
import alvarengacarlos.customer.CreateAccountUseCase;
import alvarengacarlos.exeption.ApplicationException;
import alvarengacarlos.repository.DatabaseExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("CreateAccountUseCase")
public class CreateAccountUseCaseTests {
    private CreateAccountUseCase createAccountUseCase;
    private AccountRepository accountRepository;

    @BeforeEach
    void beforeEach() {
        accountRepository = mock(AccountRepository.class);
        createAccountUseCase = new CreateAccountUseCase(accountRepository);
    }

    @Test
    @DisplayName("should create an account")
    void shouldCreateAnAccount() throws DatabaseExecutionException, ApplicationException {
        Account account = new Account("John Doe", "johndoe@email.com", "johndoe@123");
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        var input = new CreateAccountUseCase.CreateAccountDtoInput(
                account.getName(),
                account.getEmail(),
                account.getPassword()
        );
        var output = createAccountUseCase.execute(input);

        assertEquals(output.name(), account.getName());
        assertEquals(output.email(), account.getEmail());
    }

    @Test
    @DisplayName("should throw ApplicationException")
    void shouldThrowApplicationException() throws DatabaseExecutionException {
        Account account = new Account("John Doe", "johndoe@email.com", "johndoe@123");
        when(accountRepository.save(any(Account.class))).thenThrow(DatabaseExecutionException.class);

        assertThrows(ApplicationException.class, () -> {
            var input = new CreateAccountUseCase.CreateAccountDtoInput(
                    account.getName(),
                    account.getEmail(),
                    account.getPassword()
            );
            createAccountUseCase.execute(input);
        });
    }
}
