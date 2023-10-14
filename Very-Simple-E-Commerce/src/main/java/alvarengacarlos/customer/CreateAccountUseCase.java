package alvarengacarlos.customer;

import alvarengacarlos.exeption.ApplicationException;
import alvarengacarlos.repository.DatabaseExecutionException;

public class CreateAccountUseCase {
    private AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public CreateAccountDtoOutput execute(CreateAccountDtoInput dto) throws ApplicationException {
        Account account = new Account(
                dto.name(),
                dto.email(),
                dto.password()
        );

        try {
            accountRepository.save(account);

        } catch (DatabaseExecutionException e) {
            throw new ApplicationException(e.getMessage());
        }

        return new CreateAccountDtoOutput(account.getId(), dto.name(), dto.email());
    }

    public record CreateAccountDtoInput(String name, String email, String password) {}

    public record CreateAccountDtoOutput(Long id, String name, String email) {}
}
