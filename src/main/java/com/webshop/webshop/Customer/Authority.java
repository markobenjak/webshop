package com.webshop.webshop.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Authority {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int AuthorityId;

    private String AuthorityName;

    public Authority(int id,String authority) {
        AuthorityId = id;
        AuthorityName = authority;
    }

    public int getId() {
        return AuthorityId;
    }

    public String getAuthority() {
        return AuthorityName;
    }

    public void setAuthority(String authority) {
        AuthorityName = authority;
    }


}
