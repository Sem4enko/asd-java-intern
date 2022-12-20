package team.asd.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import team.asd.constant.SupportSplitPaymentEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupportSplitPaymentEnumHandler extends EnumTypeHandler<SupportSplitPaymentEnum> {

	public SupportSplitPaymentEnumHandler() {
		super(SupportSplitPaymentEnum.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, SupportSplitPaymentEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public SupportSplitPaymentEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return SupportSplitPaymentEnum.getByInt(value);
	}

	@Override
	public SupportSplitPaymentEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return SupportSplitPaymentEnum.getByInt(value);
	}
}
