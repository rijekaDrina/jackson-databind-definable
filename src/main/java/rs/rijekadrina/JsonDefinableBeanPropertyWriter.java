package rs.rijekadrina;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;

public class JsonDefinableBeanPropertyWriter extends BeanPropertyWriter {
	private static final long serialVersionUID = 1L;

	protected JsonDefinableBeanPropertyWriter(BeanPropertyWriter base) {
		super(base);
	}

	protected JsonDefinableBeanPropertyWriter(BeanPropertyWriter base, PropertyName newName) {
		super(base, newName);
	}

	@Override
	protected BeanPropertyWriter _new(PropertyName newName) {
		return new JsonDefinableBeanPropertyWriter(this, newName);
	}

	@Override
	public BeanPropertyWriter unwrappingWriter(NameTransformer unwrapper) {
		return new UnwrappingJsonDefinableBeanPropertyWriter(this, unwrapper);
	}

	@Override
	public void serializeAsField(Object bean, JsonGenerator jgen, SerializerProvider prov) throws Exception {
		Object value = get(bean);
		// If the value is undefined or if the serializer would handle a null value,
		// skip serializing this field.
		if (JsonDefinable.undefined().equals(value) || (_nullSerializer == null && value == null)) {
			return;
		}
		super.serializeAsField(bean, jgen, prov);
	}
}
