
package com.codebase.development.springinlinedb.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.codebase.development.springinlinedb.model.UserAccountDetails;

/**
 * @author Bhaskar
 *
 */
public interface CashTransactionManagementDao {

	List<UserAccountDetails> getUserAccountDetails();

	UserAccountDetails findUserAccountByUserId(String userId);

	void updateRedeemPoints(String userId, Integer balanceAmount, Integer redeemPoints);

	UserAccountDetails findUserAccountByAccountNumber(Integer accountNumber);

	void updateBalanceAmount(Integer accountNumber, Integer balanceAmount);

	void updateRedeemPoints(String userId, Integer redeemPoints);
}
