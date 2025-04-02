package rs.rijekadrina;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.UnwrapByDefault;
import javax.validation.valueextraction.ValueExtractor;

/**
 * Extractor for JsonDefinable (classic javax-validation version)
 */
@UnwrapByDefault
public class JsonDefinableValueExtractor implements ValueExtractor<JsonDefinable<@ExtractedValue ?>> {
	@Override
	public void extractValues(JsonDefinable<?> originalValue, ValueReceiver receiver) {
		JsonDefinableValueExtractorHelper.extractValues(originalValue, receiver::value);
	}
}
