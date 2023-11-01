package com.desarrolloweb.redsocial.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword extends User{
    private String passwordNew;
    private String passwordConfirm;
}
