package rs.rijekadrina;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;

import java.lang.reflect.Type;

public class JsonDefinableTypeModifier extends TypeModifier {

	@Override
	public JavaType modifyType(JavaType type, Type jdkType, TypeBindings bindings, TypeFactory typeFactory) {
		if (type.isReferenceType() || type.isContainerType()) {
			return type;
		}
		final Class<?> raw = type.getRawClass();
		JavaType refType;
		if (raw == JsonDefinable.class) {
			refType = type.containedTypeOrUnknown(0);
		} else {
			return type;
		}
		return ReferenceType.upgradeFrom(type, refType);
	}
}
