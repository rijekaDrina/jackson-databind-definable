package rs.rijekadrina;

import java.io.Serial;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class JsonDefinable<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	// A singleton representing an undefined value.
	private static final JsonDefinable<?> UNDEFINED = new JsonDefinable<>(null, false);

	private final T value;
	private final boolean isPresent;

	private JsonDefinable(T value, boolean isPresent) {
		this.value = value;
		this.isPresent = isPresent;
	}

	/**
	 * Returns a JsonDefineable representing an undefined value.
	 *
	 * @param <T> the type parameter
	 * @return an undefined JsonDefineable
	 */
	public static <T> JsonDefinable<T> undefined() {
		@SuppressWarnings("unchecked")
		JsonDefinable<T> t = (JsonDefinable<T>) UNDEFINED;
		return t;
	}

	/**
	 * Creates a JsonDefineable with the given non-null value.
	 *
	 * @param value the value to be wrapped; must not be null
	 * @param <T>   the type of the value
	 * @return a JsonDefineable wrapping the value
	 * @throws IllegalArgumentException if the value is null
	 */
	public static <T> JsonDefinable<T> of(T value) {
		if (value == null) {
			throw new IllegalArgumentException("JsonDefineable cannot be created with a null value");
		}
		return new JsonDefinable<>(value, true);
	}

	/**
	 * Returns the contained value if present.
	 *
	 * @return the value
	 * @throws NoSuchElementException if no value is present (undefined)
	 */
	public T get() {
		if (!isPresent) {
			throw new NoSuchElementException("Value is undefined");
		}
		return value;
	}

	/**
	 * Returns the contained value if present, otherwise returns the other value.
	 *
	 * @param other the value to be returned if no value is present
	 * @return the contained value if present; otherwise the other value
	 */
	public T orElse(T other) {
		return isPresent ? value : other;
	}

	/**
	 * Checks if a value is present.
	 *
	 * @return true if a value is present, false if undefined
	 */
	public boolean isPresent() {
		return isPresent;
	}

	/**
	 * If a value is present, performs the given action with the value.
	 *
	 * @param action the action to be performed if a value is present
	 */
	public void ifPresent(Consumer<? super T> action) {
		if (isPresent) {
			action.accept(value);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof JsonDefinable<?> other)) {
			return false;
		}
		return Objects.equals(value, other.value) && isPresent == other.isPresent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, isPresent);
	}

	@Override
	public String toString() {
		return isPresent ? String.format("JsonDefineable[%s]", value) : "JsonDefineable.undefined";
	}
}

