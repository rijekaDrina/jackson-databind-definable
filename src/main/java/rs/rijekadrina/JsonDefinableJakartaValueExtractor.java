package rs.rijekadrina;

import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.UnwrapByDefault;
import jakarta.validation.valueextraction.ValueExtractor;

/**
 * Extractor for JsonDefinable (modern jakarta-validation version)
 */
@UnwrapByDefault
public class JsonDefinableJakartaValueExtractor implements ValueExtractor<JsonDefinable<@ExtractedValue ?>> {
	@Override
	public void extractValues(JsonDefinable<?> originalValue, ValueReceiver receiver) {
		JsonDefinableValueExtractorHelper.extractValues(originalValue, receiver::value);
	}
}
