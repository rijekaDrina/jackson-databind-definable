package rs.rijekadrina;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ReferenceType;

public class JsonDefinableSerializers extends Serializers.Base {

	@Override
	public JsonSerializer<?> findReferenceSerializer(SerializationConfig config,
																	 ReferenceType refType, BeanDescription beanDesc,
																	 TypeSerializer contentTypeSerializer,
																	 JsonSerializer<Object> contentValueSerializer) {
		if (JsonDefinable.class.isAssignableFrom(refType.getRawClass())) {
			boolean staticTyping = (contentTypeSerializer == null)
					&& config.isEnabled(MapperFeature.USE_STATIC_TYPING);
			return new JsonDefinableSerializer(refType, staticTyping, contentTypeSerializer, contentValueSerializer);
		}
		return null;
	}
}
