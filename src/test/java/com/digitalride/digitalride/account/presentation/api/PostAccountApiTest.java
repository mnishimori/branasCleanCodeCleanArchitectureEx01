package com.digitalride.digitalride.account.presentation.api;

import static com.digitalride.digitalride.account.testData.AccountTestData.ACCOUNT_INPUT;
import static com.digitalride.digitalride.account.testData.AccountTestData.ACCOUNT_TEMPLATE_INPUT;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_CAR_PLATE;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_CPF;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_EMAIL;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_IS_DRIVER;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_IS_PASSENGER;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_NAME;
import static com.digitalride.digitalride.account.testData.AccountTestData.createNewAccount;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
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

  private void createAndPersistAccount() {
    var account = createNewAccount();
    entityManager.persist(account);
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

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"12345", "@!-+*()&^%", "email.domain.com", "@"})
  void shouldReturnBadRequestWhenAccountEmailIsInvalid(String email) throws Exception {
    var content = ACCOUNT_TEMPLATE_INPUT.formatted(email, DEFAULT_ACCOUNT_NAME,
        DEFAULT_ACCOUNT_CPF, DEFAULT_ACCOUNT_CAR_PLATE, DEFAULT_ACCOUNT_IS_PASSENGER,
        DEFAULT_ACCOUNT_IS_DRIVER);

    var request = post(URL_ACCOUNTS)
        .contentType(APPLICATION_JSON)
        .content(content);

    mockMvc.perform(request).andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturnBadRequestWhenAccountEmailAlreadyExists() throws Exception {
    createAndPersistAccount();

    var request = post(URL_ACCOUNTS)
        .contentType(APPLICATION_JSON)
        .content(ACCOUNT_INPUT);

    mockMvc.perform(request).andExpect(status().isConflict());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"12345", "@!-+*()&^%"})
  void shouldReturnBadRequestWhenAccountNameIsInvalid(String name) throws Exception {
    var content = ACCOUNT_TEMPLATE_INPUT.formatted(DEFAULT_ACCOUNT_EMAIL, name,
        DEFAULT_ACCOUNT_CPF, DEFAULT_ACCOUNT_CAR_PLATE, DEFAULT_ACCOUNT_IS_PASSENGER,
        DEFAULT_ACCOUNT_IS_DRIVER);

    var request = post(URL_ACCOUNTS)
        .contentType(APPLICATION_JSON)
        .content(content);

    mockMvc.perform(request).andExpect(status().isBadRequest());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"abc", "12345678901", "1234", "11111111111", "22222222222",
      "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888",
      "99999999999", "00000000000" })
  void shouldReturnBadRequestWhenAccountCpfIsInvalid(String cpf) throws Exception {
    var content = ACCOUNT_TEMPLATE_INPUT.formatted(DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_NAME,
        cpf, DEFAULT_ACCOUNT_CAR_PLATE, DEFAULT_ACCOUNT_IS_PASSENGER,
        DEFAULT_ACCOUNT_IS_DRIVER);

    var request = post(URL_ACCOUNTS)
        .contentType(APPLICATION_JSON)
        .content(content);

    mockMvc.perform(request).andExpect(status().isBadRequest());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"email@domain.com", "@", "1234", "AB-1234", "AB1234", ""})
  void shouldReturnBadRequestWhenAccountCarPlateIsInvalid(String carPlate) throws Exception {
    var content = ACCOUNT_TEMPLATE_INPUT.formatted(DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_NAME,
        DEFAULT_ACCOUNT_CPF, carPlate, DEFAULT_ACCOUNT_IS_PASSENGER,
        DEFAULT_ACCOUNT_IS_DRIVER);

    var request = post(URL_ACCOUNTS)
        .contentType(APPLICATION_JSON)
        .content(content);

    mockMvc.perform(request).andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturnBadRequestWhenPassengerDriverIsInvalid() throws Exception {
    var content = ACCOUNT_TEMPLATE_INPUT.formatted(DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_NAME,
        DEFAULT_ACCOUNT_CPF, DEFAULT_ACCOUNT_CAR_PLATE, "false", "false");

    var request = post(URL_ACCOUNTS)
        .contentType(APPLICATION_JSON)
        .content(content);

    mockMvc.perform(request).andExpect(status().isBadRequest());
  }
}