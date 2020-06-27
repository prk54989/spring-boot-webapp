
package com.codebase.development.springinlinedb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.codebase.development.springinlinedb.config.QueryProperties;
import com.codebase.development.springinlinedb.constants.Constants;
import com.codebase.development.springinlinedb.model.UserAccountDetails;
import com.codebase.development.springinlinedb.model.exception.CashTransactionManagementException;

/**
 * @author Bhaskar
 *
 */
@Repository
@Transactional
public class CashTransactionManagementDaoImpl implements CashTransactionManagementDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CashTransactionManagementDaoImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private QueryProperties queryProperties;

	class UserAccountDetailsMapper implements RowMapper<UserAccountDetails> {
		@Override
		public UserAccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserAccountDetails userAccountDetails = new UserAccountDetails();
			userAccountDetails.setAccountNo(rs.getInt(Constants.ACCOUNT_NUMBER));
			userAccountDetails.setUserName(rs.getString(Constants.USER_NAME));
			userAccountDetails.setLoginId(rs.getString(Constants.LOGIN_ID));
			userAccountDetails.setPassword(rs.getString(Constants.PASSWORD));
			userAccountDetails.setPremiumUser(rs.getString(Constants.PREMIUM_USER));
			userAccountDetails.setBalanceAmount(rs.getInt(Constants.BALANCE_AMOUNT));
			userAccountDetails.setRedeemPoints(rs.getInt(Constants.REDEEM_POINTS));

			return userAccountDetails;
		}
	}

	@Override
	public List<UserAccountDetails> getUserAccountDetails() {
		List<UserAccountDetails> userAccountDetails = new ArrayList<>();
		try {
			LOGGER.debug("Get All UserAccountDetails Sql Query - {} ", queryProperties.getAllUserAccountDetails());
			userAccountDetails = namedParameterJdbcTemplate.query(queryProperties.getAllUserAccountDetails(),
					new UserAccountDetailsMapper());
		} catch (DataAccessException dataAccessException) {
			throw new CashTransactionManagementException("Get UserAccountDetails Exception", dataAccessException);
		}
		LOGGER.debug("UserAccountDetails Response Count  - {} ", userAccountDetails.size());
		return userAccountDetails;
	}

	@Override
	public UserAccountDetails findUserAccountByUserId(String userId) {
		UserAccountDetails userAccountDetails = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.USER_ID, userId);
			LOGGER.debug("FindUserAccountBy UserId {} and Sql Query - {} ", userId,
					queryProperties.getUserAccountDetailsByUserId());
			userAccountDetails = namedParameterJdbcTemplate.queryForObject(
					queryProperties.getUserAccountDetailsByUserId(), mapSqlParameterSource,
					new UserAccountDetailsMapper());
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UserAccountDetails By UserId  - {} and an Exception - {} ", userId, dataAccessException);
		}
		LOGGER.debug("UserAccountDetails by UserId Response   - {} ", userAccountDetails);
		return userAccountDetails;
	}

	@Override

	public void updateRedeemPoints(String userId, Integer balanceAmount, Integer redeemPoints) {
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.USER_ID, userId);
			mapSqlParameterSource.addValue(Constants.BALANCE_AMOUNT, balanceAmount);
			mapSqlParameterSource.addValue("redeem_points", redeemPoints);
			int status = namedParameterJdbcTemplate.update(queryProperties.getUpdateUserAccountDetailsByUserId(),
					mapSqlParameterSource);
			LOGGER.debug("UpdateRedeemPoints Update Status  - {} ", status);
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UpdateRedeemPoints By UserId  - {} and an Exception - {} ", userId, dataAccessException);
		}
	}

	@Override
	public UserAccountDetails findUserAccountByAccountNumber(Integer accountnumber) {
		UserAccountDetails userAccountDetails = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.ACCOUNT_NUMBER, accountnumber);

			LOGGER.debug("Find User Account By AccountNumber {} and Sql Query - {} ", accountnumber,
					queryProperties.getUserAccountDetailsByAccountNumber());

			userAccountDetails = namedParameterJdbcTemplate.queryForObject(
					queryProperties.getUserAccountDetailsByAccountNumber(), mapSqlParameterSource,
					new UserAccountDetailsMapper());
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("Find User Account By AccountNumber - {} and an Exception - {} ", accountnumber,
					dataAccessException);
		}
		LOGGER.debug("Find User Account By AccountNumber Response   - {} ", userAccountDetails);
		return userAccountDetails;
	}

	public void updateBalanceAmount(Integer accountNumber, Integer balanceAmount) {
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.ACCOUNT_NUMBER, accountNumber);
			mapSqlParameterSource.addValue(Constants.BALANCE_AMOUNT, balanceAmount);
			int status = namedParameterJdbcTemplate.update(queryProperties.getUpdateUserAccountDetailsByAccountNumber(),
					mapSqlParameterSource);
			LOGGER.debug("updateBalanceAmount Update Status  - {} ", status);
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("updateBalanceAmount By AccountNumber  - {} and an Exception - {} ", accountNumber,
					dataAccessException);
		}

	}

	@Override
	public void updateRedeemPoints(String userId, Integer redeemPoints) {
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.USER_ID, userId);
			mapSqlParameterSource.addValue("redeem_points", redeemPoints);
			int status = namedParameterJdbcTemplate.update(queryProperties.getUpdateUserAccountRedeemPointsByUserId(),
					mapSqlParameterSource);
			LOGGER.debug("updateBalanceAmount Update Status  - {} ", status);
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UserAccountDetails By UserId  - {} and an Exception - {} ", userId, dataAccessException);
		}
	}
}
