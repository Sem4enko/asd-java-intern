package team.asd.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import team.asd.constant.PendingTransactionStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PendingTransactionStatusHandler extends EnumTypeHandler<PendingTransactionStatus> {

	public PendingTransactionStatusHandler() {
		super(PendingTransactionStatus.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PendingTransactionStatus parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public PendingTransactionStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return PendingTransactionStatus.getByInt(value);
	}

	@Override
	public PendingTransactionStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return PendingTransactionStatus.getByInt(value);
	}
}
