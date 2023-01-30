package projectbackend.SecurityRestControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import projectbackend.dto.account.AccountDto;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //test username = null
    @Test
    public void login_username_13() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(null);
        accountDto.setPassword("123345");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test username = ""
    @Test
    public void login_username_14() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("");
        accountDto.setPassword("123345");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test username invalid format
    @Test
    public void login_username_15() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("$");
        accountDto.setPassword("123345");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test username minlength
    @Test
    public void login_username_16() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("a");
        accountDto.setPassword("123345");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test username maxlength
    @Test
    public void login_username_17() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccccccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccc");
        accountDto.setPassword("123345");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test password = null
    @Test
    public void login_password_13() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("admin");
        accountDto.setPassword(null);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test password = ""
    @Test
    public void login_password_14() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("admin");
        accountDto.setPassword("");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test password minlength
    @Test
    public void login_password_16() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("admin");
        accountDto.setPassword("343");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test password maxlength
    @Test
    public void login_password_17() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("admin");
        accountDto.setPassword("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccccccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccc");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //test login success
    @Test
    public void login_18() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("admin");
        accountDto.setPassword("123456");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
