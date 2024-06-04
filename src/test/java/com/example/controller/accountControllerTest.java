package com.example.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.example.service.accountService;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class accountControllerTest {





    @Mock
    private accountService mockAccountService;

    private accountController accountController;
    accountService accountRepository;
    
    private HttpSession session = mock(HttpSession.class);
    private HttpSession HttpSession() {
        return mock(HttpSession.class);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountController = new accountController();
        accountController.as = mockAccountService;
    }

    @Test
    void testDeposit() {
        String username = "test_user";
        float amount = 100.0f;

        String result = accountController.deposit(username, amount);

        verify(mockAccountService).deposit(username, amount);
        assertEquals("deposit successfull", result);
    }

    @Test
    void testWithdrawalSuccessful() {
        String username = "test_user";
        float amount = 50.0f;
        ModelAndView expectedResult = new ModelAndView("withdrawalResult");
        expectedResult.addObject("message", "Withdrawal successful");

        // Mock the behavior of the service method
        when(mockAccountService.withdrawal(username, amount)).thenReturn(true);

        // Call the controller method
        ModelAndView result = accountController.withdrawal(username, amount);

        // Verify the result
        assertEquals(expectedResult.getViewName(), result.getViewName());
        assertEquals(expectedResult.getModel().get("message"), result.getModel().get("message"));
    }


    @Test
    void testWithdrawalInsufficientBalance() {
        String username = "test_user";
        float amount = 200.0f;
        boolean withdrawalSuccessful = false;
        ModelAndView expectedModelAndView = new ModelAndView();
        expectedModelAndView.addObject("message", "Withdrawal unsuccessful: Insufficient balance");

        // Call the withdrawal method
        withMockedSession(username, () -> {
            ModelAndView modelAndView = accountController.withdrawal(username, amount);
            assertEquals("withdrawalResult", modelAndView.getViewName());
            assertEquals(expectedModelAndView.getModel().get("message"), modelAndView.getModel().get("message"));
        });
    }
    @Test
    public void testCheckBalance() {
        String username = "test_user";
        float expectedBalance = 150.0f;
        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("username")).thenReturn(username);
        when(mockAccountService.checkBalance(username)).thenReturn(expectedBalance);

        float actualBalance = accountController.checkBalance(mockSession);

        verify(mockSession).getAttribute("username");
        verify(mockAccountService).checkBalance(username);
        assertEquals(expectedBalance, actualBalance);
    }
    private void withMockedSession(String username, Runnable action) {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("username")).thenReturn(username);
        action.run();
    }
   
    @Test
    void testIndexPage() {
        String username = "test_user";
        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("username")).thenReturn(username);

        ModelAndView modelAndView = accountController.indexPage(mockSession);

        verify(mockSession).getAttribute("username");
        assertEquals("indexPage", modelAndView.getViewName());
        assertEquals(username, modelAndView.getModel().get("username"));
    }

    @Test
    void testDepositPage() {
        ModelAndView modelAndView = accountController.depositPage();

        assertEquals("depositPage", modelAndView.getViewName());
    }

    @Test
    void testChangePasswordPage() {
        ModelAndView modelAndView = accountController.changePasswordPage();

        assertEquals("changePasswordPage", modelAndView.getViewName());
    }

    @Test
    void testWithdrawalPage() {
        ModelAndView modelAndView = accountController.withdrawalPage();

        assertEquals("withdrawalPage", modelAndView.getViewName());
    }
    @Test
    void testCheckBalancePage() {
        ModelAndView modelAndView = accountController.checkBalancePage();

        assertEquals("checkBalancePage", modelAndView.getViewName());
    }

    @Test
    void testEditProfile() {
        ModelAndView modelAndView = accountController.editProfile();

        assertEquals("editProfile", modelAndView.getViewName());
    }

    @Test
    void testEmailPage() {
        ModelAndView modelAndView = accountController.EmailPage();

        assertEquals("EmailPage", modelAndView.getViewName());
    }

    @Test
    void testPhonePage() {
        ModelAndView modelAndView = accountController.PhonePage();

        assertEquals("PhonePage", modelAndView.getViewName());
    }

    @Test
    void testAddressPage() {
        ModelAndView modelAndView = accountController.AddressPage();

        assertEquals("AddressPage", modelAndView.getViewName());
    }
    @Test
    void testChangeEmail() {
        String username = "testUser";
        String email = "newemail@example.com";

        // Call the controller method
        String result = accountController.changeEmail(username, email);

        // Verify that the service method was called with the correct arguments
        verify(mockAccountService).changeEmail(username, email);

        // Verify the result
        assertEquals("email changed successfully", result);
    }
    @Test
    void testChangePhone() {
        String username = "testUser";
        String phone = "9986868680";

        // Call the controller method
        String result = accountController.changePhone(username, phone);

        // Verify that the service method was called with the correct arguments
        verify(mockAccountService).changePhone(username, phone);

        // Verify the result
        assertEquals("phone changed  successfully", result);
    }
    @Test
    void testChangeAddress() {
        String username = "testUser";
        String address= "Bangalore";

        // Call the controller method
        String result = accountController.changeAddress(username, address);

        // Verify that the service method was called with the correct arguments
        verify(mockAccountService).changeAddress(username, address);

        // Verify the result
        assertEquals("address changed  successfully", result);
    }
    @Test
    void testChangePassword() {
        String username = "testUser";
        String password = "1122";

        // Call the controller method
        String result = accountController.changePassword(username, password);

        // Verify that the service method was called with the correct arguments
        verify(mockAccountService).changePassword(username, password);

        // Verify the result
        assertEquals("password changed successfully", result);
    }
    @Test
    void testLoginSuccessful() {
        String username = "testUser";
        String password = "testPassword";

        // Mock the behavior of the service method
        when(mockAccountService.login(username, password)).thenReturn(true);

        // Call the controller method
        ModelAndView result = accountController.loginacc(username, password, session);

        // Verify that the service method was called with the correct arguments
        verify(mockAccountService).login(username, password);

        // Verify the view name and session attribute
        assertEquals("redirect:/account/indexPage", result.getViewName());
        verify(session).setAttribute("username", username);
    }

    @Test
    void testLoginFailed() {
        String username = "testUser";
        String password = "wrongPassword";

        // Mock the behavior of the service method
        when(mockAccountService.login(username, password)).thenReturn(false);

        // Call the controller method
        ModelAndView result = accountController.loginacc(username, password, session);

        // Verify that the service method was called with the correct arguments
        verify(mockAccountService).login(username, password);

        // Verify the view name and loginFailed attribute
        assertEquals("login", result.getViewName());
        assertTrue((boolean) result.getModel().get("loginFailed"));
    }
    @Test
    void testLogin() {
        // Create a new instance of the accountController
        accountController accountController = new accountController();

        // Call the login method
        ModelAndView modelAndView = accountController.login();

        // Verify the result
        assertEquals("login", modelAndView.getViewName());
    }

}
