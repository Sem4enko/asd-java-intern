package team.asd.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import team.asd.constant.PaymentProcessingTypeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentProcessingTypeEnumHandler extends EnumTypeHandler<PaymentProcessingTypeEnum> {

	public PaymentProcessingTypeEnumHandler() {
		super(PaymentProcessingTypeEnum.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PaymentProcessingTypeEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public PaymentProcessingTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return PaymentProcessingTypeEnum.getByInt(value);
	}

	@Override
	public PaymentProcessingTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return PaymentProcessingTypeEnum.getByInt(value);
	}
}
