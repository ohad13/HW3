package Domain;

public class AssignmentPolicy extends Policy {
    String method; // "random" or "serial"

    public AssignmentPolicy(String method) {
        this.method = method;
    }

    public String getValue() {
        return method;
    }
}
