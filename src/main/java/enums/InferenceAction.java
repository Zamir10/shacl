package enums;

public enum InferenceAction {
  CREATE("create"),
  DELETE("delete"),
  UPDATE("update");

  String value;

  private InferenceAction(String value) {
    this.value = value;
  }
}
