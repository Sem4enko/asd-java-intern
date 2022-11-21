package team.asd.constant;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FundsHolderEnumHandler extends EnumOrdinalTypeHandler<FundsHolderEnum> {

	public FundsHolderEnumHandler(Class<FundsHolderEnum> type) {
		super(type);
	}

		@Override
	public void setNonNullParameter(PreparedStatement ps, int i, FundsHolderEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public FundsHolderEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return FundsHolderEnum.getByInt(value);
	}

	@Override
	public FundsHolderEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return FundsHolderEnum.getByInt(value);
	}
}
