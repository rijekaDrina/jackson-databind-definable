package rs.rijekadrina;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;

import java.io.IOException;
import java.io.Serial;

public class JsonDefinableDeserializer extends ReferenceTypeDeserializer<JsonDefinable<Object>> {
	@Serial
	private static final long serialVersionUID = 1L;

	private boolean isStringDeserializer = false;

	public JsonDefinableDeserializer(JavaType fullType, ValueInstantiator inst,
												TypeDeserializer typeDeser, JsonDeserializer<?> deser) {
		super(fullType, inst, typeDeser, deser);
		if (fullType instanceof ReferenceType && ((ReferenceType) fullType).getReferencedType() != null) {
			this.isStringDeserializer = ((ReferenceType) fullType).getReferencedType().isTypeOrSubTypeOf(String.class);
		}
	}

	@Override
	public JsonDefinable<Object> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		JsonToken t = p.getCurrentToken();
		if (t == JsonToken.VALUE_STRING && !isStringDeserializer) {
			String str = p.getText().trim();
			if (str.isEmpty()) {
				return JsonDefinable.undefined();
			}
		}
		return super.deserialize(p, ctxt);
	}

	@Override
	public JsonDefinableDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer<?> valueDeser) {
		return new JsonDefinableDeserializer(_fullType, _valueInstantiator, typeDeser, valueDeser);
	}

	@Override
	public Object getAbsentValue(DeserializationContext ctxt) {
		return JsonDefinable.undefined();
	}

	@Override
	public JsonDefinable<Object> getNullValue(DeserializationContext ctxt) {
		// Since defined null values are disallowed, we return undefined here.
		return JsonDefinable.undefined();
	}

	@Override
	public Object getEmptyValue(DeserializationContext ctxt) {
		return JsonDefinable.undefined();
	}

	@Override
	public JsonDefinable<Object> referenceValue(Object contents) {
		// This will throw an exception if contents is null.
		return JsonDefinable.of(contents);
	}

	@Override
	public Object getReferenced(JsonDefinable<Object> reference) {
		return reference.get();
	}

	@Override
	public JsonDefinable<Object> updateReference(JsonDefinable<Object> reference, Object contents) {
		// Again, JsonDefinable.of(contents) will enforce non-null.
		return JsonDefinable.of(contents);
	}

	@Override
	public Boolean supportsUpdate(DeserializationConfig config) {
		return Boolean.TRUE;
	}
}

