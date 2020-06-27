package com.codebase.development.springinlinedb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codebase.development.springinlinedb.model.UserAccountDetails;

/**
 * @author Bhaskar
 *
 */
public interface CashTransactionManagementService {

	List<UserAccountDetails> getUserAccountDetails();

	UserAccountDetails findUserAccountByUserId(String userId);
	
	void updateRedeemPoints(String userId, Integer balanceAmount, Integer redeemPoints);

	void updateRedeemPoints(String userId, Integer redeemPoints);

}
