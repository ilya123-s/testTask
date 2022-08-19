package troshko.test_task.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    VIEWER, ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
