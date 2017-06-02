
public enum UserRole {
  ROLE_USER(1), ROLE_ADMIN(2);

  private final int value;

  UserRole(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
