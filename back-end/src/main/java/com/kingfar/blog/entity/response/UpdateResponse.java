package com.kingfar.blog.entity.response;

import lombok.Data;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class UpdateResponse extends Response {
    public static UpdateResponse successResp = new UpdateResponse(0, "success");
    public static UpdateResponse UsernameDuplicatedResp = new UpdateResponse(1, "username duplicated");
    public static UpdateResponse failedToUpdatePublicInfoResp = new UpdateResponse(2, "fail to update public info");
    public static UpdateResponse samePasswordResp = new UpdateResponse(3, "don't use password you are using now");
    public static UpdateResponse wrongPasswordResp = new UpdateResponse(4, "wrong password");
    public static UpdateResponse failedToUpdatePasswordResp = new UpdateResponse(5, "failed to update password");

    @Override
    protected int groupCode() {
        return 4;
    }

    public UpdateResponse(int code, String msg) {
        super(code, msg);
    }
}
