package team.asd.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import team.asd.constant.FundsHolderEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FundsHolderEnumHandler extends EnumTypeHandler<FundsHolderEnum> {

	public FundsHolderEnumHandler() {
		super(FundsHolderEnum.class);
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
