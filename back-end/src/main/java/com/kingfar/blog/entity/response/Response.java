package com.kingfar.blog.entity.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class Response {
    private int code;
    private String msg;

    protected abstract int groupCode();

    protected int groupCodeMultiplier() {
        return 100;
    }

    public Response(int code, String msg) {
        this.code = code + groupCode() * groupCodeMultiplier();
        this.msg = msg;
    }
}
