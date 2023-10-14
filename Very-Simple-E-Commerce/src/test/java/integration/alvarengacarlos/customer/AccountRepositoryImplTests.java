package integration.alvarengacarlos.customer;

import alvarengacarlos.customer.Account;
import alvarengacarlos.customer.AccountRepositoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("AccountRepositoryImpl")
public class AccountRepositoryImplTests {
    private AccountRepositoryImpl accountRepository;

    @BeforeEach
    void beforeEach() {
        accountRepository = new AccountRepositoryImpl();
    }

    @Test
    @DisplayName("should find an account by id")
    void shouldFindAnAccountById() {
        Account account = new Account("John Doe", "johndoe@email.com", "johndoe@123");

        assertDoesNotThrow(() -> {
            accountRepository.save(account);

            Account founded = accountRepository.find(account.getId());

            assertEquals(founded.getId(), account.getId());
        });
    }

    @Test
    @DisplayName("should save an account")
    void shouldSaveAnAccount() {
        Account account = new Account("John Doe", "johndoe@email.com", "johndoe@123");

        assertDoesNotThrow(() -> {
            Account saved = accountRepository.save(account);

            assertEquals(saved, account);
        });
    }

    @Test
    @DisplayName("should update an account by id")
    void shouldUpdateAnAccountById() {
        Account account = new Account("John Doe", "johndoe@email.com", "johndoe@123");
        Account accountToUpdate = new Account("John D", "johnd@mail.com", "johnd@123");

        assertDoesNotThrow(() -> {
            accountRepository.save(account);

            Account updated = accountRepository.update(accountToUpdate, account.getId());

            assertEquals(updated.getId(), account.getId());
            assertNotEquals(updated.getName(), account.getName());
            assertNotEquals(updated.getEmail(), account.getEmail());
            assertNotEquals(updated.getPassword(), account.getPassword());
        });
    }

    @Test
    @DisplayName("should delete an account by id")
    void shouldDeleteAnAccountById() {
        Account account = new Account("John Doe", "johndoe@email.com", "johndoe@123");

        assertDoesNotThrow(() -> {
            accountRepository.save(account);

            Long id = accountRepository.delete(account.getId());

            assertEquals(id, account.getId());
        });
    }
}
