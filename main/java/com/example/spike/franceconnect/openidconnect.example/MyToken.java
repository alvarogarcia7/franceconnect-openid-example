package com.example.spike.franceconnect.openidconnect.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MyToken {

    String accessToken;
    String tokenType;
    String expiresIn;
    String idToken;

    public MyToken(String accessToken, String tokenType, String expiresIn, String idToken) {

        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.idToken = idToken;
    }

}
