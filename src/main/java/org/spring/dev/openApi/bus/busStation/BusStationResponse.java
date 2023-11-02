package org.spring.dev.openApi.bus.busStation;

import lombok.Data;

@Data
public class BusStationResponse {

  private ComMsgHeader comMsgHeader;

  private MsgHeader msgHeader;

  private MsgBody msgBody;
}
