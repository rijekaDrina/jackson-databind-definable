package rs.rijekadrina;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.List;

public class JsonDefinableBeanSerializerModifier extends BeanSerializerModifier {
	@Override
	public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
																	 BeanDescription beanDesc,
																	 List<BeanPropertyWriter> beanProperties) {
		for (int i = 0; i < beanProperties.size(); ++i) {
			final BeanPropertyWriter writer = beanProperties.get(i);
			JavaType type = writer.getType();
			if (type.isTypeOrSubTypeOf(JsonDefinable.class)) {
				beanProperties.set(i, new JsonDefinableBeanPropertyWriter(writer));
			}
		}
		return beanProperties;
	}
}

