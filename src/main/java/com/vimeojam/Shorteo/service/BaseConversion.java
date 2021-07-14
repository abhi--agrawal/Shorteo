package com.vimeojam.Shorteo.service;

import org.springframework.stereotype.Service;
import com.devskiller.friendly_id.FriendlyId;

import java.util.UUID;

@Service
public class BaseConversion {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();
    private final int base = allowedCharacters.length;

    public String encode(UUID input){
        return FriendlyId.toFriendlyId(input);
    }

    public UUID decode(String input) {
        return FriendlyId.toUuid(input);
    }
}
