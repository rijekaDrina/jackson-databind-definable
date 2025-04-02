package rs.rijekadrina;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;

import java.io.Serial;

public class JsonDefinableSerializer extends ReferenceTypeSerializer<JsonDefinable<?>> {

	@Serial
	private static final long serialVersionUID = 1L;

	protected JsonDefinableSerializer(ReferenceType fullType, boolean staticTyping,
												 TypeSerializer vts, JsonSerializer<Object> ser) {
		super(fullType, staticTyping, vts, ser);
	}

	protected JsonDefinableSerializer(JsonDefinableSerializer base, BeanProperty property,
												 TypeSerializer vts, JsonSerializer<?> valueSer, NameTransformer unwrapper,
												 Object suppressableValue) {
		// Keep suppressNulls to false to always serialize JsonDefinable[null] if needed.
		super(base, property, vts, valueSer, unwrapper, suppressableValue, false);
	}

	@Override
	protected ReferenceTypeSerializer<JsonDefinable<?>> withResolved(BeanProperty prop,
																						  TypeSerializer vts, JsonSerializer<?> valueSer,
																						  NameTransformer unwrapper) {
		return new JsonDefinableSerializer(this, prop, vts, valueSer, unwrapper, _suppressableValue);
	}

	@Override
	public ReferenceTypeSerializer<JsonDefinable<?>> withContentInclusion(Object suppressableValue,
																								 boolean suppressNulls) {
		return new JsonDefinableSerializer(this, _property, _valueTypeSerializer,
				_valueSerializer, _unwrapper, suppressableValue);
	}

	@Override
	protected boolean _isValuePresent(JsonDefinable<?> value) {
		return value.isPresent();
	}

	@Override
	protected Object _getReferenced(JsonDefinable<?> value) {
		return value.get();
	}

	@Override
	protected Object _getReferencedIfPresent(JsonDefinable<?> value) {
		return value.isPresent() ? value.get() : null;
	}
}
