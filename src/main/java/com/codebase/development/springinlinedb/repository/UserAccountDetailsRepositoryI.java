/**
 *  Harsha
 */
package com.codebase.development.springinlinedb.repository;

import java.util.List;

import com.codebase.development.springinlinedb.model.TransactionDetails;

/**
 * @author Bhaskar
 *
 */
public interface UserAccountDetailsRepositoryI {

	Integer insertCashTransaction(Integer fromAccountNumber, Integer balaceAmount, String transferType,
			Integer toAccountNumber, Integer transferAmount, String remarks);

	List<TransactionDetails> accountTransactonsForDebit(Integer userId);

	List<TransactionDetails> accountTransactonsForCredit(Integer userId);

}
