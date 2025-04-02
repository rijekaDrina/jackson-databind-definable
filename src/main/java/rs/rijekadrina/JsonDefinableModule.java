package rs.rijekadrina;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.Module;

public class JsonDefinableModule extends Module {

	private final String NAME = "JsonDefinableModule";

	@Override
	public void setupModule(SetupContext context) {
		context.addSerializers(new JsonDefinableSerializers());
		context.addDeserializers(new JsonDefinableDeserializers());
		// Modify type info for JsonDefinable
		context.addTypeModifier(new JsonDefinableTypeModifier());
		context.addBeanSerializerModifier(new JsonDefinableBeanSerializerModifier());
	}

	@Override
	public Version version() {
		return PackageVersion.VERSION;
	}

	@Override
	public int hashCode() {
		return NAME.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
	}

	@Override
	public String getModuleName() {
		return NAME;
	}
}
