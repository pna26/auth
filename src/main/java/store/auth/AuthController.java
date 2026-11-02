package store.auth;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import store.account.AccountOut;

@FeignClient(
    name="auth",
    url="http://auth:8080"
)
public interface AuthController {

    public static final String AUTH_COOKIE_TOKEN = "__store_jwt_token";

    @PostMapping("/auth/register")
    public ResponseEntity<Void> create(
        @RequestBody RegisterIn in
    );

    @PostMapping("/auth/login")
    public ResponseEntity<Void> login(
        @RequestBody LoginIn in
    );

    @GetMapping("/auth/me")
    public ResponseEntity<AccountOut> me(
        @RequestHeader(value = "id-account", required = true) String idAccount
    );

    @PostMapping("/auth/solve")
    public ResponseEntity<Map<String, String>> solveToken(
        @RequestBody TokenOut map
    );

    @GetMapping("/auth/logout")
    public ResponseEntity<Void> logout();

    @GetMapping("/auth/health-check")
    public ResponseEntity<Void> healthCheck();

}
