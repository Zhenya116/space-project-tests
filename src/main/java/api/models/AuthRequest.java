package api.models;

public class AuthRequest {
    private final String username;
    private final String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static AuthRequest of(String username, String password) {
        return new AuthRequest(username, password);
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
