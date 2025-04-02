package rs.rijekadrina;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;

import java.io.Serial;

public class UnwrappingJsonDefinableBeanPropertyWriter extends UnwrappingBeanPropertyWriter {
	@Serial
	private static final long serialVersionUID = 1L;

	public UnwrappingJsonDefinableBeanPropertyWriter(BeanPropertyWriter base,
																	 NameTransformer transformer) {
		super(base, transformer);
	}

	protected UnwrappingJsonDefinableBeanPropertyWriter(UnwrappingBeanPropertyWriter base,
																		 NameTransformer transformer, SerializedString name) {
		super(base, transformer, name);
	}

	@Override
	protected UnwrappingBeanPropertyWriter _new(NameTransformer transformer, SerializedString newName) {
		return new UnwrappingJsonDefinableBeanPropertyWriter(this, transformer, newName);
	}

	@Override
	public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
		Object value = get(bean);
		if (JsonDefinable.undefined().equals(value) || (_nullSerializer == null && value == null)) {
			return;
		}
		super.serializeAsField(bean, gen, prov);
	}
}
