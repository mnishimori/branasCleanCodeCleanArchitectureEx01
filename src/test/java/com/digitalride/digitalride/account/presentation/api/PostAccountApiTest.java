package com.digitalride.digitalride.account.presentation.api;

import static com.digitalride.digitalride.account.testData.AccountTestData.ACCOUNT_INPUT;
import static com.digitalride.digitalride.shared.presentation.IsUUID.isUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.digitalride.digitalride.account.model.entity.Account;
import com.digitalride.digitalride.account.testData.AccountTestData;
import com.digitalride.digitalride.shared.annotation.DatabaseTest;
import com.digitalride.digitalride.shared.annotation.IntegrationTest;
import com.jayway.jsonpath.JsonPath;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
@DatabaseTest
class PostAccountApiTest {

  private static final String URL_ACCOUNTS = "/accounts";
  private final MockMvc mockMvc;
  private final EntityManager entityManager;


  @Autowired
  PostAccountApiTest(MockMvc mockMvc, EntityManager entityManager) {
    this.mockMvc = mockMvc;
    this.entityManager = entityManager;
  }

  @Test
  void shouldCreateUser() throws Exception {
    var request = post(URL_ACCOUNTS)
        .contentType(APPLICATION_JSON)
        .content(ACCOUNT_INPUT);
    var mvcResult = mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.id", isUUID()))
        .andReturn();

    var contentAsString = mvcResult.getResponse().getContentAsString();
    var id = JsonPath.parse(contentAsString).read("$.id").toString();
    var accountFound = entityManager.find(Account.class, UUID.fromString(id));
    assertThat(accountFound).isNotNull();
    assertThat(accountFound.getEmail()).isEqualTo(AccountTestData.DEFAULT_ACCOUNT_EMAIL);
    assertThat(accountFound.getName()).isEqualTo(AccountTestData.DEFAULT_ACCOUNT_NAME);
    assertThat(accountFound.getCpf()).isEqualTo(AccountTestData.DEFAULT_ACCOUNT_CPF);
  }
}