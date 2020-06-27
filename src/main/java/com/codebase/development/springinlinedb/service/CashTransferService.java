package com.codebase.development.springinlinedb.service;

import org.springframework.ui.Model;

import com.codebase.development.springinlinedb.model.UserAccountDetails;

public interface CashTransferService {

	boolean cashTransfer(UserAccountDetails fromUserAccountDetails, UserAccountDetails toUserAccountDetails, int amount,
			String transferType, Model model);
}
