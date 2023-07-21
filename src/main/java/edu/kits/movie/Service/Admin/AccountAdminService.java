package edu.kits.movie.Service.Admin;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.AccountAdminResponse;
import edu.kits.movie.Entity.Account;
import edu.kits.movie.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAdminService {
    private final AccountRepository accountRepository;
    private final ModelConverter converter;
    public PaginationResponse<AccountAdminResponse> getAllAccounts(Pageable pageable){
        Page<Account> accounts = accountRepository.findAll(pageable);
        return new PaginationResponse<>(
                accounts.getNumber(),
                accounts.getSize(),
                accounts.getTotalPages(),
                converter.mapAllByIterator(accounts.getContent(),AccountAdminResponse.class)
        );
    }


}
