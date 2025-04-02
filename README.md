JsonDefinable wrapper class and Jackson module to support handling domain objects which cannot contain null values.

Example:
```java
public record BankAccountNumber(String value) {

   private static final String ACCOUNT_REGEX = "^555-[0-9]{3}-[0-9]{8}-[0-9]{2}$";
   private static final int LENGTH = 19;

   @JsonCreator
   public BankAccountNumber {
      if (value == null || value.isBlank()) {
         throw new IllegalArgumentException("The account number is a required field.");
      }
      if (value.length() != LENGTH) {
         throw new IllegalArgumentException("Bank account number must be exactly 19 characters long.");
      }
      if (!value.matches(ACCOUNT_REGEX)) {
         throw new IllegalArgumentException("Bank account number must be in the format 555-YYY-ZZZZZZZZ-WW.");
      }
   }

   @JsonValue
   public String value() {
      return value;
   }

   @Override
   public String toString() {
      return value;
   }
}
```

The record from above cannot be used in tandem with `JsonNullable` instead we can use `JsonDefinable`:

```java
public record BankUpdateRequest(
		@Schema(description = "The bank account number", implementation = BankAccountNumber.class)
      JsonDefinable<BankAccountNumber> accountNumber,
		// Other fields...
) {
}
```
