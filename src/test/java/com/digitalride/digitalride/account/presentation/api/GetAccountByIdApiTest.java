package com.digitalride.digitalride.account.presentation.api;

import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_UUID_STRING;
import static com.digitalride.digitalride.account.testData.AccountTestData.createNewAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.digitalride.digitalride.account.model.entity.Account;
import com.digitalride.digitalride.account.presentation.api.util.JsonUtil;
import com.digitalride.digitalride.account.presentation.dto.AccountOutputDto;
import com.digitalride.digitalride.shared.annotation.DatabaseTest;
import com.digitalride.digitalride.shared.annotation.IntegrationTest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
@DatabaseTest
class GetAccountByIdApiTest {

  private static final String URL_ACCOUNTS = "/accounts/";
  private final MockMvc mockMvc;
  private final EntityManager entityManager;

  @Autowired
  GetAccountByIdApiTest(MockMvc mockMvc, EntityManager entityManager) {
    this.mockMvc = mockMvc;
    this.entityManager = entityManager;
  }

  private Account createAndPersistAccount() {
    var account = createNewAccount();
    return entityManager.merge(account);
  }

  @Test
  void shouldFindAccountByIdWhenAccountExists() throws Exception {
    var account = createAndPersistAccount();
    var accountExpected = AccountOutputDto.from(account);

    var request = get(URL_ACCOUNTS + account.getId());
    var mvcResult = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andReturn();

    var contentAsString = mvcResult.getResponse().getContentAsString();
    var userFound = JsonUtil.fromJson(contentAsString, Account.class);
    var userDtoFound = AccountOutputDto.from(userFound);
    assertThat(userDtoFound).usingRecursiveComparison().isEqualTo(accountExpected);
  }

  @Test
  void shouldReturnNotFoundWhenAccountDoesNotExist() throws Exception {
    var request = get(URL_ACCOUNTS + DEFAULT_ACCOUNT_UUID_STRING);
    mockMvc.perform(request).andExpect(status().isNotFound());
  }

  @Test
  void shouldReturnBadRequestWhenAccountUuidIsInvalid() throws Exception {
    var request = get(URL_ACCOUNTS + "aaa");
    mockMvc.perform(request).andExpect(status().isBadRequest());
  }
}
