package rs.rijekadrina;

abstract class JsonDefinableValueExtractorHelper {
	public static void extractValues(JsonDefinable<?> originalValue, ValueSetter valueSetter) {
		if (originalValue.isPresent()) {
			valueSetter.apply(null, originalValue.get());
		}
	}

	@FunctionalInterface
	interface ValueSetter {
		void apply(String var1, Object var2);
	}
}
