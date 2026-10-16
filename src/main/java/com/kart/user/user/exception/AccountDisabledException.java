package com.kart.user.user.exception;

import java.util.UUID;

public class AccountDisabledException extends RuntimeException {
    public AccountDisabledException(UUID uuid) {
        super("Account " + uuid + " is disabled");
    }
}
