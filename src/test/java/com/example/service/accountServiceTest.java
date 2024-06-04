package com.example.service;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.example.controller.accountController;
import com.example.model.account;
import com.example.repo.accountRepo;

import java.util.Optional;

	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	class accountServiceTest {

	    @Mock
	    private accountRepo mockAccountRepo;

	    private accountService accountService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        accountService = new accountService(mockAccountRepo);
	    }

	    @Test
	    void testDeposit() {
	        String username = "test_user";
	        float amount = 100.0f;

	        accountService.deposit(username, amount);

	        verify(mockAccountRepo).deposit(username, amount);
	    }

	    @Test
	    void testWithdrawalSuccessful() {
	        String username = "test_user";
	        float amount = 50.0f;
	        account mockAccount = new account();
	        mockAccount.setBalance(100.0f);
	        when(mockAccountRepo.findById(username)).thenReturn(Optional.of(mockAccount));

	        boolean result = accountService.withdrawal(username, amount);

	        verify(mockAccountRepo).withdrawal(username, amount);
	        assertTrue(result);
	    }

	    @Test
	    void testWithdrawalInsufficientBalance() {
	        String username = "test_user";
	        float amount = 200.0f;
	        account mockAccount = new account();
	        mockAccount.setBalance(100.0f);
	        when(mockAccountRepo.findById(username)).thenReturn(Optional.of(mockAccount));

	        boolean result = accountService.withdrawal(username, amount);

	        verify(mockAccountRepo, never()).withdrawal(username, amount);
	        assertFalse(result);
	    }

	    @Test
	    void testCheckBalance() {
	        String username = "test_user";
	        account mockAccount = new account();
	        mockAccount.setBalance(150.0f);
	        when(mockAccountRepo.findById(username)).thenReturn(Optional.of(mockAccount));

	        float balance = accountService.checkBalance(username);

	        verify(mockAccountRepo).findById(username);
	        assertEquals(150.0f, balance);
	    }

	    @Test
	    void testChangePassword() {
	        String username = "test_user";
	        String newPassword = "new_password";

	        accountService.changePassword(username, newPassword);

	        verify(mockAccountRepo).changePassword(username, newPassword);
	    }

	    @Test
	    void testLoginSuccessful() {
	        String username = "test_user";
	        String correctPassword = "correct_password";
	        when(mockAccountRepo.login(username, correctPassword)).thenReturn(1);

	        boolean result = accountService.login(username, correctPassword);

	        verify(mockAccountRepo).login(username, correctPassword);
	        assertTrue(result);
	    }

	    @Test
	    void testLoginUnsuccessful() {
	        String username = "test_user";
	        String incorrectPassword = "incorrect_password";
	        when(mockAccountRepo.login(username, incorrectPassword)).thenReturn(0);

	        boolean result = accountService.login(username, incorrectPassword);

	        verify(mockAccountRepo).login(username, incorrectPassword);
	        assertFalse(result);
	    }

	    @Test
	    void testChangeEmail() {
	        String username = "test_user";
	        String newEmail = "new_email@example.com";

	        accountService.changeEmail(username, newEmail);

	        verify(mockAccountRepo).changeEmail(username, newEmail);
	    }

	    @Test
	    void testChangePhone() {
	        String username = "test_user";
	        String newPhone = "123-456-7890";

	        accountService.changePhone(username, newPhone);

	        verify(mockAccountRepo).changePhone(username, newPhone);
	    }

	    @Test
	    void testChangeAddress() {
	        String username = "test_user";
	        String newAddress = "123 Main St, City";

	        accountService.changeAddress(username, newAddress);

	        verify(mockAccountRepo).changeAddress(username, newAddress);
	    }
	
	    @Test
	    public void testeditProfile() {
	        // Arrange
	        accountController controller = new accountController();

	        // Act
	        ModelAndView modelAndView = controller.editProfile();

	        // Assert
	        assertEquals("editProfile", modelAndView.getViewName());
	    }
	}


